# 🤖 AI Resume Analyzer

An AI-powered Resume Analyzer that evaluates resumes against job descriptions and provides match scores, 
skill gap analysis, and improvement suggestions using **Spring Boot + Spring AI + React**.

---

## 🚀 Features

- 📄 Upload Resume (PDF/DOC)  
- 🧾 Input Job Description  
- 🤖 AI-based Resume Analysis  
- 📊 Match Score Calculation  
- 🧠 Skill Gap Identification  
- 💡 Personalized Suggestions  
- 🔐 Secure Backend APIs  

---

## 🛠️ Tech Stack

### 🔹 Backend
- Java  
- Spring Boot  
- Spring AI (LLM Integration)  
- REST APIs  
- Spring Security (JWT - optional)  

### 🔹 AI Integration
- Gemini / OpenAI APIs  
- Prompt Engineering (Basic)  

### 🔹 Frontend
- ReactJS  
- HTML, CSS, JavaScript  

### 🔹 Database
- MySQL  

### 🔹 Tools & Deployment
- Docker  
- Postman  

---

## ⚙️ How It Works

1. User uploads a resume and enters a job description  
2. Backend extracts text from the resume  
3. Spring AI sends structured prompts to the LLM  
4. AI analyzes:
   - Skills match  
   - Experience relevance  
   - Missing keywords  
5. System returns:
   - Match score (%)  
   - Skill gaps  
   - Improvement suggestions  

---

## 🤖 Role of Spring AI

- Acts as a bridge between backend and LLM APIs  
- Sends prompts containing resume and job description  
- Receives structured AI responses  
- Helps generate:
  - Match score  
  - Skill analysis  
  - Recommendations  

---

## 📌 Sample Use Case

- Job seekers can evaluate their resume before applying  
- Identify missing skills based on job requirements  
- Improve resume quality using AI suggestions  

---

## 🔐 Environment Variables

```env
SPRING_AI_API_KEY=your_api_key_here
