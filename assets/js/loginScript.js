async function performLogin() {
  console.log("Form submission intercepted");
  const usernameInput = document.getElementById("usernameInput").value;
  const passwordInput = document.getElementById("passwordInput").value;
  const roleInput = document.getElementById("roleSelector").value.toUpperCase();

  try {
    const response = await fetch(
      `http://localhost:8080/users/username/${usernameInput}`
    );
    if (!response.ok) {
      throw new Error("User Not Found");
    }
    const data = await response.json();
    if (data.password === passwordInput && data.role === roleInput) {
      console.log("Login successful");
      console.log("Username:", data.username);
      console.log("Password:", data.password);
      console.log("Role:", data.role);

      localStorage.setItem("userId", data.id);
      localStorage.setItem("nameOfUser", data.name);
      console.log(sessionStorage.getItem("userId"));

      if (roleInput === "STUDENT") {
        window.location.href = "studentPage.html";
      } else if (roleInput === "FACULTY") {
        window.location.href = "facultyPage.html";
      } else if (roleInput === "ADMINISTRATOR") {
        window.location.href = "adminPage.html";
      } else {
        console.error("Incorrect password or role");
      }
    } else {
      console.error("Incorrect password or role");
    }
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
  }
}
