const profilePhoto = document.getElementsByTagName("img");
const studentName = document.getElementById("profileName");
const studentUserId = document.getElementById("studentUserId");
const studentDeparmentId = document.getElementById("studentDeparmentId");
const studentYear = document.getElementById("studentYear");

const userId = localStorage.getItem("userId");
const userName = localStorage.getItem("nameOfUser");

async function getStudentDetails() {
  try {
    const response = await fetch(`http://localhost:8080/student/${userId}`);
    if (!response.ok) {
      throw new Error("Student Not Found");
    }

    const data = await response.json();
    profilePhoto[0].src = data.photo;
    studentName.textContent = userName;
    studentUserId.textContent = `User ID: ${userId}`;
    studentDeparmentId.textContent = `Department: ${data.department.name}`;
    studentYear.textContent = `Academic Year: ${data.year}`;
  } catch (error) {
    console.error(error);
  }
}

getStudentDetails();

async function getFacultyList() {
  try {
    const response = await fetch(
      `http://localhost:8080/student/${userId}/faculties`
    );
    if (!response.ok) {
      throw new Error("Student Not Found");
    }

    const data = await response.json();

    displayFaculties(data);
  } catch (error) {
    console.error(error);
  }
}

function displayFaculties(FacultyList) {
  const container = document.getElementById("facultyListContainer");

  container.innerHTML = "";

  FacultyList.forEach((faculty) => {
    const facultyDiv = document.createElement("div");

    facultyDiv.classList = "facultyList";

    facultyDiv.innerHTML = `
      <span class="facultyName">${faculty.facultyName}</span>
      <span class="facultyEmail">${faculty.email}</span>
      <span class="facultyPhone">${faculty.phone}</span>
      <span class="departmentName">${faculty.departmentName}</span>`;

    container.appendChild(facultyDiv);
  });
}

getFacultyList();

async function getStudentSearch() {
  document
    .getElementById("searchForm")
    .addEventListener("submit", async function (event) {
      event.preventDefault();

      const year = document.getElementById("year").value;

      const department = Number(document.getElementById("department").value);

      try {
        const response = await fetch(
          `http://localhost:8080/student/${year}/${department}`
        );
        if (!response.ok) {
          throw new Error("there is no student");
        }

        const data = await response.json();
        displayStudentsSearchList(data);
      } catch (error) {
        console.error(error);
      }
    });
}

function displayStudentsSearchList(studentsList) {
  const container = document.getElementById("searchResultsForm");

  container.innerHTML = "";

  studentsList.forEach((student) => {
    const newDiv = document.createElement("div");
    newDiv.classList = "searchStudentList";

    newDiv.innerHTML = `
    <span class="studentName">${student.user.name}</span>
    <span class="email">${student.user.email}</span>
    <span class="phone no">${student.user.phone}</span>
    <span class="department name">${student.department.name}</span>
    `;
    container.appendChild(newDiv);
  });
}

getStudentSearch();
