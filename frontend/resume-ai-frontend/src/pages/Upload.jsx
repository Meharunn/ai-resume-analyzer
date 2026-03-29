import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";   // ✅ FIXED
import "../styles/upload.css";

export default function Upload() {

  const [file, setFile] = useState(null);
  const [jd, setJd] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  // pages/Upload.js  (only the handleSubmit function - replace it)
  const handleSubmit = async () => {
    if (!file || !jd.trim()) {
      alert("Please upload a resume and enter Job Description");
      return;
    }

    try {
      setLoading(true);

      const formData = new FormData();
      formData.append("file", file);
      formData.append("jd", jd);

      const res = await axios.post(
        "http://localhost:8080/resume/upload",
        formData,
        {
          headers: { "Content-Type": "multipart/form-data" }
        }
      );

      console.log("✅ Gemini Response:", res.data);   // Helpful for debugging

      // Navigate to result page with data
      navigate("/result", { state: res.data });

    } catch (err) {
      console.error("Full Error:", err);

      const errorMsg = err.response?.data?.message
                     || err.response?.data
                     || err.message
                     || "Failed to analyze resume. Please try again.";

      alert(errorMsg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="upload-container">

      <h1 className="upload-title">AI Resume Analyzer</h1>

      <p className="upload-subtitle">
        "Struggling to match your resume with job requirements?
        Let AI guide you to your dream job."
      </p>

      <div className="upload-card">

        <label>Upload Resume</label>
        <input
          type="file"
          onChange={(e) => setFile(e.target.files[0])}
        />

        <label>Paste Job Description</label>
        <textarea
          placeholder="Paste job description here..."
          value={jd}
          onChange={(e) => setJd(e.target.value)}
        />

        <button onClick={handleSubmit} disabled={loading}>
          {loading ? <div className="spinner"></div> : "Analyze Resume"}
        </button>

      </div>
    </div>
  );
}