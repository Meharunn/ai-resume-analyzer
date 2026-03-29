import { useState } from "react";
import AuthLayout from "../components/AuthLayout";
import { Link, useNavigate } from "react-router-dom";
import API from "../api/api";

export default function Register() {

  const [form, setForm] = useState({
    name: "",
    email: "",
    password: ""
  });

  const navigate = useNavigate(); // ✅ added

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      const res = await API.post("/auth/register", form);

      if (res.data.success) {
        alert(res.data.message);
        navigate("/login");  // ✅ only on success
      } else {
        alert(res.data.message);
      }

    } catch (err) {
      alert(err.response?.data?.message || "Registration failed");
    }
  };

  return (
    <AuthLayout title="Create a New Account" subtitle="Join Resume AI today">

      <form onSubmit={handleRegister} autoComplete="off">

        {/* fake fields */}
        <input type="text" name="fakeuser" style={{ display: "none" }} />
        <input type="password" name="fakepass" style={{ display: "none" }} />

        {/* ✅ NAME FIELD (missing before) */}
        <input
          type="text"
          placeholder="Full Name"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
        />

        <input
          type="email"
          placeholder="Email Address"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        />

        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        />

        <button type="submit">Sign Up</button>

      </form>

      <p className="link">
        Already have an account? <Link to="/login">Log in</Link>
      </p>

    </AuthLayout>
  );
}