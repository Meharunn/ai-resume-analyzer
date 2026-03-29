import { useState } from "react";
import AuthLayout from "../components/AuthLayout";
import { Link, useNavigate } from "react-router-dom";
import API from "../api/api";

export default function Login() {

  const [form, setForm] = useState({
    email: "",
    password: ""
  });

  const navigate = useNavigate(); // ✅ for routing

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await API.post("/auth/login", form);

      // ✅ CHECK SUCCESS
      if (res.data.success) {

        alert(res.data.message);

        localStorage.setItem("userId", res.data.userId || 1);

        navigate("/upload");

      } else {
        // ❌ show error but DO NOT navigate
        alert(res.data.message);
      }

    } catch (err) {
      alert(err.response?.data?.message || "Login failed");
    }
  };

  return (
    <AuthLayout
      title="Welcome Back"
      subtitle="Login to your account"
    >

      {/* ✅ FORM MUST HAVE onSubmit */}
      <form onSubmit={handleLogin} autoComplete="off">

        {/* 🔥 fake fields to prevent autofill */}
        <input type="text" name="fakeuser" style={{ display: "none" }} />
        <input type="password" name="fakepass" style={{ display: "none" }} />

        {/* EMAIL */}
        <input
          type="email"
          placeholder="Email Address"
          value={form.email}
          onChange={(e) =>
            setForm({ ...form, email: e.target.value })
          }
        />

        {/* PASSWORD */}
        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={(e) =>
            setForm({ ...form, password: e.target.value })
          }
        />

        {/* ✅ BUTTON MUST BE submit */}
        <button type="submit">Login</button>

      </form>

      <p className="link">
        Don't have an account? <Link to="/">Register</Link>
      </p>

    </AuthLayout>
  );
}