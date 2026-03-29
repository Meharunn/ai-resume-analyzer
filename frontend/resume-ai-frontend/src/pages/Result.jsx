import { useLocation, useNavigate } from "react-router-dom";
import "../styles/result.css";

export default function Result() {
  const location = useLocation();
  const navigate = useNavigate();
  const data = location.state;

  if (!data) {
    return (
      <div className="result-container">
        <div className="result-card">
          <h2>No Analysis Data Found</h2>
          <p>Please upload your resume and job description first.</p>
          <button onClick={() => navigate("/upload")}>
            Go Back to Upload
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="result-container">
      {/* Score Card */}
      <div className="score-card">
        <h2>Resume Match Score</h2>
        <h1>{data.matchScore || "0%"}</h1>
      </div>

      {/* Three Column Grid */}
      <div className="result-grid">
        {/* Matched Skills */}
        <div className="glass-box">
          <h3 className="matched">✅ Matched Skills</h3>
          <ul>
            {data.matchedSkills?.length > 0 ? (
              data.matchedSkills.map((skill, i) => (
                <li key={i}>{skill}</li>
              ))
            ) : (
              <li>No matched skills found</li>
            )}
          </ul>
        </div>

        {/* Missing Skills */}
        <div className="glass-box">
          <h3 className="missing">❌ Missing Skills</h3>
          <ul>
            {data.missingSkills?.length > 0 ? (
              data.missingSkills.map((skill, i) => (
                <li key={i}>{skill}</li>
              ))
            ) : (
              <li>No missing skills — Great match!</li>
            )}
          </ul>
        </div>

        {/* Suggestions */}
        <div className="glass-box">
          <h3 className="suggestion">💡 Suggestions to Improve</h3>
          <ul>
            {data.suggestions?.length > 0 ? (
              data.suggestions.map((suggestion, i) => (
                <li key={i}>{suggestion}</li>
              ))
            ) : (
              <li>No suggestions available</li>
            )}
          </ul>
        </div>
      </div>

      <button
        className="upload-again-btn"
        onClick={() => navigate("/upload")}
      >
        Analyze Another Resume
      </button>
    </div>
  );
}