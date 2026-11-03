import { useEffect, useMemo, useState } from 'react'
import './App.css'

const PRODUCT_LIST_API_URL = '/api/product-list'
const PRODUCT_MANAGEMENT_API_URL = '/api/product-management'
const PRODUCT_REGISTRATION_API_URL = '/api/product-registration'
const AUTH_STATUS_URL = '/api/auth/status'
const LOGIN_URL = '/api/login'
const LOGOUT_URL = '/api/logout'

/* ========== ヘッダー ========== */
function Header({ auth, onLogout, loginOnly = false }) {
  return (
      <header className="topbar">
        <a className="brand" href="/product-list">商品管理フォーム</a>

        {!loginOnly && (
            <nav className="nav">
              <a href="/product-list">商品一覧</a>

              {auth.isAdmin && (
                  <a href="/product-management">商品管理</a>
              )}

              {!auth.authenticated && (
                  <a href="/login">ログイン</a>
              )}

              {!auth.authenticated && (
                  <span className="login-status">未ログイン状態</span>
              )}

              {auth.authenticated && (
                  <form onSubmit={onLogout}>
              <span className="login-status">
                <span style={{ color: auth.isAdmin ? '#f97316' : '#2563eb' }}>
                  {auth.username}
                </span>
                {' '}でログイン中
              </span>

                    <button type="submit">ログアウト</button>
                  </form>
              )}
            </nav>
        )}
      </header>
  )
}

/* ========== ログイン画面 ========== */
function LoginPage({ auth }) {
  const params = new URLSearchParams(window.location.search)
  const hasLogout = params.has('logout')

  const [loginError, setLoginError] = useState(false)
  const [showPassword, setShowPassword] = useState(false)

  const login = async (event) => {
    event.preventDefault()
    setLoginError(false)

    const formData = new FormData(event.currentTarget)
    const body = new URLSearchParams(formData)

    try {
      const response = await fetch(LOGIN_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        credentials: 'include',
        body,
      })

      if (!response.ok) {
        setLoginError(true)
        return
      }

      window.location.href = '/product-list'
    } catch {
      setLoginError(true)
    }
  }

  return (
      <>
        <Header auth={auth} loginOnly />

        <main className="container narrow">
          <section className="page-head access-denied">
            <h1>ログイン</h1>
            <br />
            <p>ユーザー名とパスワードを入力してください。</p>
          </section>

          <form className="form" onSubmit={login}>
            {loginError && (
                <p className="alert">
                  ユーザー名またはパスワードが違います。
                </p>
            )}

            {hasLogout && (
                <p className="notice">ログアウトしました。</p>
            )}

            <label>
              ユーザー名
              <input
                  name="username"
                  autoComplete="username"
                  placeholder="ユーザー名を入力してください"
                  required
              />
            </label>

            <label>
              パスワード
              <div className="password-field">
                <input
                    name="password"
                    type={showPassword ? 'text' : 'password'}
                    autoComplete="current-password"
                    placeholder="パスワードを入力してください"
                    required
                />

                <button
                    type="button"
                    className="password-toggle"
                    onClick={() => setShowPassword(!showPassword)}
                >
                  {showPassword ? '非表示' : '表示'}
                </button>
              </div>
            </label>

            <button type="submit">ログイン</button>
          </form>
        </main>
      </>
  )
}

/* ========== 商品一覧 ========== */
function ProductList({ products, loading }) {
  const [searchKeyword, setSearchKeyword] = useState('')

  if (loading) {
    return <p className="empty">商品を読み込み中です。</p>
  }

  if (products.length === 0) {
    return <p className="empty">表示できる商品はまだありません。</p>
  }

  const filteredProducts = products.filter((product) =>
      product.name.includes(searchKeyword.trim())
  )

  return (
      <>
        <div className="product-search">
          <input
              value={searchKeyword}
              onChange={(event) => setSearchKeyword(event.target.value)}
              placeholder="商品名で検索"
          />
        </div>

        {filteredProducts.length === 0 ? (
            <p className="empty">
              検索条件に一致する商品はありません。
            </p>
        ) : (
            <section className="grid">
              {filteredProducts.map((product) => (
                  <article className="product-card" key={product.id}>
                    <h2>{product.name}</h2>
                    <p>
                      <span>¥</span>
                      <span>{product.price.toLocaleString()}</span>
                    </p>
                  </article>
              ))}
            </section>
        )}
      </>
  )
}

function ProductListPage({
                           auth,
                           onLogout,
                           products,
                           loading,
                           message,
                         }) {
  return (
      <>
        <Header auth={auth} onLogout={onLogout} />

        <main className="container">
          <section className="page-head">
            <h1>商品一覧</h1>
            <p>商品一覧の公開ページです。</p>

            {auth.isAdmin && (
                <p>
                  商品管理のタブから商品管理画面に遷移できます。
                </p>
            )}
          </section>

          {message && <p className="alert">{message}</p>}

          <ProductList
              products={products}
              loading={loading}
          />
        </main>
      </>
  )
}

/* ========== 商品管理 ========== */
function ProductManagement({
                             products,
                             loading,
                             onTogglePublished,
                           }) {
  if (loading) {
    return <p className="empty">商品を読み込み中です。</p>
  }

  if (products.length === 0) {
    return <p className="empty">管理できる商品はまだありません。</p>
  }

  return (
      <section className="table-card">
        <table className="table">
          <thead>
          <tr>
            <th>商品ID</th>
            <th>商品名</th>
            <th>値段</th>
            <th>在庫数</th>
            <th>公開 / 非公開</th>
            <th></th>
          </tr>
          </thead>

          <tbody>
          {products.map((p) => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.name}</td>
                <td>¥{p.price.toLocaleString()}</td>
                <td>{p.stock}</td>
                <td>{p.published ? '公開' : '非公開'}</td>

                <td className="actions">
                  <button
                      type="button"
                      onClick={() => onTogglePublished(p)}
                  >
                    {p.published ? '非公開にする' : '公開する'}
                  </button>
                </td>
              </tr>
          ))}
          </tbody>
        </table>
      </section>
  )
}

function ProductManagementPage({
                                 auth,
                                 onLogout,
                                 products,
                                 loading,
                                 message,
                                 onTogglePublished,
                                 registrationComplete,
                               }) {
  return (
      <>
        <Header auth={auth} onLogout={onLogout} />

        <main className="container">
          <section className="page-head page-head-row">
            <div>
              <h1>商品管理</h1>
              <p>
                商品情報を管理するページです。
                公開 / 非公開を切り替えることができます。
              </p>
              <p>
                ※公開状態にしている商品のみが商品一覧画面に表示されます。
              </p>

              {/* 商品登録完了メッセージ */}
              {registrationComplete && (
                  <p className="notice">
                    商品を登録しました。
                  </p>
              )}
            </div>

            <a className="btn" href="/product-registration">
              商品を登録する
            </a>
          </section>

          {message && <p className="alert">{message}</p>}

          <ProductManagement
              products={products}
              loading={loading}
              onTogglePublished={onTogglePublished}
          />
        </main>
      </>
  )
}

/* ========== 商品登録 ========== */
function ProductRegistrationPage({
                                   auth,
                                   onLogout,
                                   message,
                                   onRegister,
                                   submitting,
                                 }) {
  const [published, setPublished] = useState(false)

  return (
      <>
        <Header auth={auth} onLogout={onLogout} />

        <main className="container narrow">
          <section className="page-head">
            <h1>商品登録</h1>
            <p>商品を登録することができるページです。</p>
            <p>
              ※「公開する」のチェックを有効にすると公開状態に設定できます。
            </p>
          </section>

          <form className="form" onSubmit={onRegister}>
            {message && <p className="alert">{message}</p>}

            <label>
              商品名
              <input name="name" required />
            </label>

            <label>
              値段
              <input
                  name="price"
                  type="number"
                  min="0"
                  required
              />
            </label>

            <label>
              在庫数
              <input
                  name="stock"
                  type="number"
                  min="0"
                  required
              />
            </label>

            <label className="check">
              <input
                  name="published"
                  type="checkbox"
                  checked={published}
                  onChange={(event) =>
                      setPublished(event.target.checked)
                  }
              />
              公開する
            </label>

            {/* チェックボックスの状態に応じて文言を切り替える */}
            <p>
              {published
                  ? '公開の状態で商品を登録します。'
                  : '非公開の状態で商品を登録します。'}
            </p>

            {/* 補足説明 */}
            <p>
              ※「公開する」のチェックボックスのチェックを有効にした状態で登録した場合は商品一覧画面に商品情報が表示されます。
            </p>

            <button type="submit" disabled={submitting}>
              {submitting ? '登録中...' : '登録する'}
            </button>
          </form>
        </main>
      </>
  )
}

/* ========== アクセス拒否 ========== */
function AccessDeniedPage({ auth, onLogout, path }) {

  // URLに応じて表示する文章を切り替える
  const isProductManagement = path.includes('/product-management')

  const description = isProductManagement
      ? '管理者ユーザーでアクセス可能な商品管理画面では、登録されている全商品の管理をすることができます。'
      : '管理者ユーザーでアクセス可能な商品登録画面では、新しく商品を登録することができます。'

  return (
      <>
        <Header auth={auth} onLogout={onLogout} />

        <main className="container narrow access-denied-container">
          <section className="page-head access-denied">

            <p className="access-denied-error">
              商品管理、商品登録には管理者ユーザーでのログインが必要です。
            </p>

            <p className="access-denied-description">
              {description}
            </p>

            <div className="access-denied-buttons">
              <a className="btn" href="/product-list">
                商品一覧へ戻る
              </a>

              <form onSubmit={onLogout}>
                <button type="submit">
                  ログアウト
                </button>
              </form>
            </div>

          </section>
        </main>
      </>
  )
}

/* ========== App 本体 ========== */
function App() {
  const [auth, setAuth] = useState({
    authenticated: false,
    username: null,
    isAdmin: false,
  })

  const [products, setProducts] = useState([])
  const [message, setMessage] = useState('')
  const [loading, setLoading] = useState(true)
  const [accessDenied, setAccessDenied] = useState(false)
  const [submitting, setSubmitting] = useState(false)

  const path = window.location.pathname

  // URLのクエリパラメータを取得
  const params = new URLSearchParams(window.location.search)
  const registrationComplete =
      params.get('registration') === 'true'

  const adminOnlyPage =
      path === '/product-management' ||
      path === '/product-registration'

  const pageTitle = useMemo(() => {
    if (path === '/product-management') {
      return '商品管理'
    }

    if (path === '/product-registration') {
      return '商品登録'
    }

    return '商品一覧'
  }, [path])

  /* ----- ログイン状態の取得 ----- */
  const loadAuth = async () => {
    const guestAuth = {
      authenticated: false,
      username: null,
      isAdmin: false,
    }

    try {
      const response = await fetch(AUTH_STATUS_URL, {
        credentials: 'include',
      })

      if (!response.ok) {
        setAuth(guestAuth)
        return guestAuth
      }

      const currentAuth = await response.json()
      setAuth(currentAuth)

      return currentAuth
    } catch {
      setAuth(guestAuth)
      return guestAuth
    }
  }

  /* ----- 管理者権限の確認 ----- */
  const denyUnauthorizedPage = (currentAuth) => {
    if (adminOnlyPage && !currentAuth.isAdmin) {
      setAccessDenied(true)
      setLoading(false)
      return true
    }

    return false
  }

  /* ----- 商品一覧の取得 ----- */
  const loadProducts = async () => {
    setLoading(true)
    setMessage('')

    try {
      const url =
          path === '/product-management'
              ? PRODUCT_MANAGEMENT_API_URL
              : PRODUCT_LIST_API_URL

      const response = await fetch(url, {
        credentials: 'include',
      })

      if (
          response.status === 401 ||
          response.status === 403
      ) {
        throw new Error(
            '商品管理には管理者ログインが必要です。'
        )
      }

      if (!response.ok) {
        throw new Error(
            `商品一覧の取得に失敗しました。status: ${response.status}`
        )
      }

      setProducts(await response.json())
    } catch (error) {
      if (error instanceof TypeError) {
        setMessage(
            'Spring Boot APIに接続できません。バックエンドが起動しているか確認してください。'
        )
        return
      }

      setMessage(error.message)
    } finally {
      setLoading(false)
    }
  }

  /* ----- 初期処理 ----- */
  useEffect(() => {
    document.title = `商品管理フォーム - ${pageTitle}`

    const init = async () => {
      const currentAuth = await loadAuth()

      if (path === '/login') {
        setLoading(false)
        return
      }

      if (denyUnauthorizedPage(currentAuth)) {
        return
      }

      if (path === '/product-registration') {
        setLoading(false)
        return
      }

      await loadProducts()
    }

    init()

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [path])

  /* ----- ログアウト ----- */
  const logout = async (event) => {
    event.preventDefault()

    await fetch(LOGOUT_URL, {
      method: 'POST',
      credentials: 'include',
    })

    window.location.href = '/login?logout'
  }

  /* ----- 公開 / 非公開の切り替え ----- */
  const togglePublished = async (product) => {
    setMessage('')

    try {
      const action = product.published
          ? 'hide'
          : 'publish'

      const response = await fetch(
          `${PRODUCT_MANAGEMENT_API_URL}/${product.id}/${action}`,
          {
            method: 'POST',
            credentials: 'include',
          }
      )

      if (
          response.status === 401 ||
          response.status === 403
      ) {
        throw new Error(
            '公開 / 非公開の切り替えには管理者ログインが必要です。'
        )
      }

      if (!response.ok) {
        throw new Error(
            `公開状態の更新に失敗しました。status: ${response.status}`
        )
      }

      await loadProducts()
    } catch (error) {
      if (error instanceof TypeError) {
        setMessage(
            'Spring Boot APIに接続できません。バックエンドが起動しているか確認してください。'
        )
        return
      }

      setMessage(error.message)
    }
  }

  /* ----- 商品登録 ----- */
  const register = async (event) => {
    event.preventDefault()
    setMessage('')
    setSubmitting(true)

    const formData = new FormData(event.currentTarget)

    const payload = {
      name: formData.get('name'),
      price: Number(formData.get('price')),
      stock: Number(formData.get('stock')),
      published: formData.get('published') === 'on',
    }

    try {
      const response = await fetch(
          PRODUCT_REGISTRATION_API_URL,
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify(payload),
          }
      )

      if (!response.ok) {
        setMessage('入力内容を確認してください。')
        return
      }

      // 登録成功後、クエリパラメータを付けて商品管理画面へ遷移
      window.location.href = '/product-management?registration=true'

    } catch {
      setMessage('登録に失敗しました。')
    } finally {
      setSubmitting(false)
    }
  }

  /* ----- 画面の出し分け ----- */

  if (path === '/login') {
    return <LoginPage auth={auth} />
  }

  if (accessDenied) {
    return (
        <AccessDeniedPage
            auth={auth}
            onLogout={logout}
            path={path}
        />
    )
  }

  if (path === '/product-management') {
    return (
        <ProductManagementPage
            auth={auth}
            onLogout={logout}
            products={products}
            loading={loading}
            message={message}
            onTogglePublished={togglePublished}
            registrationComplete={registrationComplete}
        />
    )
  }

  if (path === '/product-registration') {
    return (
        <ProductRegistrationPage
            auth={auth}
            onLogout={logout}
            message={message}
            onRegister={register}
            submitting={submitting}
        />
    )
  }

  return (
      <ProductListPage
          auth={auth}
          onLogout={logout}
          products={products}
          loading={loading}
          message={message}
      />
  )
}

export default App
