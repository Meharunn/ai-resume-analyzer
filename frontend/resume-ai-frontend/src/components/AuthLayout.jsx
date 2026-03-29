import "../styles/auth.css";
import image from "../assets/image.jpg";

export default function AuthLayout({ children, title, subtitle }) {
  return (
    <div className="container">

      {/* LEFT IMAGE */}
      <div className="left">
        <img src={image} alt="bg" />
      </div>

      {/* RIGHT FORM */}
      <div className="right">
        <div className="glass-card">
          <h1>{title}</h1>
          <p className="subtitle">{subtitle}</p>

          {children}
        </div>
      </div>

    </div>
  );
}