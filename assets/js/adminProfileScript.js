const modalStudent = document.getElementById("modalStudent");
const addStudentBtn = document.getElementById("addStudentBtn");
const closeBtn = document.getElementById("studentClose");

const modalFaculty = document.getElementById("modalFaculty");
const addFacultyBtn = document.getElementById("addFacultyBtn");
const closeFacultyBtn = document.getElementById("facultyClose");

const modalCourse = document.getElementById("modalCourse");
const addCourseBtn = document.getElementById("addCourseBtn");
const closeCourseBtn = document.getElementById("courseClose");

const modalDepartment = document.getElementById("modalDepartment");
const addDepartmentBtn = document.getElementById("addDepartmentBtn");
const closeDepartmentBtn = document.getElementById("departmentClose");

addStudentBtn.onclick = function () {
  modalStudent.style.display = "block";
};

closeBtn.onclick = function () {
  modalStudent.style.display = "none";
};

addFacultyBtn.onclick = function () {
  modalFaculty.style.display = "block";
};

closeFacultyBtn.onclick = function () {
  modalFaculty.style.display = "none";
};

addCourseBtn.onclick = function () {
  modalCourse.style.display = "block";
};

closeCourseBtn.onclick = function () {
  modalCourse.style.display = "none";
};

addDepartmentBtn.onclick = function () {
  modalDepartment.style.display = "block";
};

closeDepartmentBtn.onclick = function () {
  modalDepartment.style.display = "none";
};

getAllStudents();
getAllFaculties();
getAllCourses();
getAllDepartments();

document
  .getElementById("studentProfileForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();
    const studentEmail = document.getElementById("studentEmail").value;
    const username = document.getElementById("studentUsername").value;
    const password = document.getElementById("studentPassword").value;
    const studentName = document.getElementById("studentName").value;
    const studentPhone = document.getElementById("studentPhone").value;
    const photoUrl = document.getElementById("studentPhotoUrl").value;
    const year = document.getElementById("year").value;
    const departmentId = document.getElementById("studentDepartmentId").value;
    const courseIds = document.getElementById("courseIds").value;

    let userId;

    const newUser = {
      username: username,
      password: password,
      role: "STUDENT",
      name: studentName,
      email: studentEmail,
      phone: studentPhone,
    };

    const newUserJson = JSON.stringify(newUser);

    const newUserOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: newUserJson,
    };

    await fetch(`http://localhost:8080/users/create`, newUserOptions)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error while creating");
        }
        return response.json();
      })
      .then((data) => {
        userId = data.id;
      });

    const newStudent = {
      photo: photoUrl,
      departmentId: departmentId,
      year: year,
    };

    const newStudentJson = JSON.stringify(newStudent);

    const newStudentOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: newStudentJson,
    };

    await fetch(
      `http://localhost:8080/student/create/${userId}`,
      newStudentOptions
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Resource updated successfully:", data);
      })
      .catch((error) => {
        console.error("There was a problem with your fetch operation:", error);
      });

    for (const courseId of courseIds) {
      const enrollment = {
        studentId: userId,
        courseId: courseId.trim(),
      };

      const enrollmentJson = JSON.stringify(enrollment);

      const enrollmentOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: enrollmentJson,
      };

      await fetch(
        `http://localhost:8080/enrollments/create/${userId}`,
        enrollmentOptions
      )
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              `Error while creating enrollment for course ID: ${courseId}`
            );
          }
          return response.json();
        })
        .then((data) => {
          console.log(
            `Enrollment created successfully for course ID: ${courseId}`,
            data
          );
        })
        .catch((error) => {
          console.error(
            `There was a problem creating enrollment for course ID: ${courseId}`,
            error
          );
        });
    }
    await getAllStudents();
  });

document
  .getElementById("facultyProfileForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const facultyEmail = document.getElementById("facultyEmail").value;
    const username = document.getElementById("facultyUsername").value;
    const password = document.getElementById("facultyPassword").value;
    const facultyName = document.getElementById("facultyName").value;
    const facultyPhone = document.getElementById("facultyPhone").value;
    const photoUrl = document.getElementById("facultyPhotoUrl").value;
    const departmentId = document.getElementById("facultyDepartmentId").value;
    const officeHours = document.getElementById("officeHours").value;

    let userId;

    const newUser = {
      username: username,
      password: password,
      role: "FACULTY",
      name: facultyName,
      email: facultyEmail,
      phone: facultyPhone,
    };

    const newUserJson = JSON.stringify(newUser);

    const newUserOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: newUserJson,
    };

    await fetch(`http://localhost:8080/users/create`, newUserOptions)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error while creating faculty user");
        }
        return response.json();
      })
      .then((data) => {
        userId = data.id;
      });

    const newFaculty = {
      photo: photoUrl,
      departmentId: departmentId,
      officeHours: officeHours,
    };

    const newFacultyJson = JSON.stringify(newFaculty);

    const newFacultyOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: newFacultyJson,
    };

    await fetch(
      `http://localhost:8080/faculty/create/${userId}`,
      newFacultyOptions
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Faculty created successfully:", data);
        modalFaculty.style.display = "none";
      })
      .catch((error) => {
        console.error("There was a problem with your fetch operation:", error);
      });
    await getAllFaculties();
  });

async function getAllFaculties() {
  try {
    const response = await fetch(`http://localhost:8080/faculty`);

    if (!response.ok) {
      throw new Error("Error getting all the faculty data");
    }

    const data = await response.json();
    const adminFacultyList = document.getElementById("adminFacultyList");
    const studentList = adminFacultyList.querySelector(".studentCourseList");
    studentList.innerHTML = "";

    data.forEach((faculty) => {
      const facultyId = faculty.user.id;
      const facultyName = faculty.user.name;
      const facultyEmail = faculty.user.email;
      const facultyPhone = faculty.user.phone;
      const facultyDepartment = faculty.department.name;
      const facultyOfficeHours = faculty.officeHours;

      const facultyEntry = document.createElement("div");
      facultyEntry.className = "studentDetails";

      facultyEntry.innerHTML = `
            <span class="studentId">User ID: ${facultyId}</span>
            <span class="studentName">Name: ${facultyName}</span>
            <span class="studentEmail">Email: ${facultyEmail}</span>
            <span class="studentPhone">Phone: ${facultyPhone}</span>
            <span class="studentDepartment">Department: ${facultyDepartment}</span>
            <span class="facultyOfficeHours">Office Hours: ${facultyOfficeHours}</span>
            <button class="updateBtn" data-user-id="${facultyId}" data-role="FACULTY" onclick="update(this)">Update</button>
            <button class="deleteBtn"  data-user-id="${facultyId}" data-role="FACULTY" onclick="deleteEntry(this)">Delete</button> 
        `;

      studentList.appendChild(facultyEntry);
    });
  } catch (error) {
    console.error(error);
    alert("An error occurred while fetching faculty data.");
  }
}

async function getAllStudents() {
  try {
    const response = await fetch(`http://localhost:8080/student`);

    if (!response.ok) {
      throw new Error("Error getting all the student data");
    }

    const data = await response.json();
    const adminStudentList = document.getElementById("adminStudentList");
    const studentList = adminStudentList.querySelector(".studentCourseList");
    studentList.innerHTML = "";

    data.forEach((student) => {
      const studentId = student.user.id;
      const studentName = student.user.name;
      const studentEmail = student.user.email;
      const studentPhone = student.user.phone;
      const studentDepartment = student.department.name;

      const studentEntry = document.createElement("div");
      studentEntry.className = "studentDetails";

      studentEntry.innerHTML = `
            <span class="studentId">User ID: ${studentId}</span>
            <span class="studentName">Name: ${studentName}</span>
            <span class="studentEmail">Email: ${studentEmail}</span>
            <span class="studentPhone">Phone: ${studentPhone}</span>
            <span class="studentDepartment">Department: ${studentDepartment}</span>
            <button class="updateBtn" data-user-id="${studentId}" data-role= "STUDENT" onclick="update(this)">Update</button>
            <button class="deleteBtn" data-user-id="${studentId}" data-role= "STUDENT" onclick="deleteEntry(this)">Delete</button> 
        `;

      studentList.appendChild(studentEntry);
    });
  } catch (error) {
    console.error(error);
    alert("An error occurred while fetching student data.");
  }
}

function addIfNotEmpty(obj, key, value) {
  if (value !== null && value.trim() !== "") {
    obj[key] = value;
  }
}

async function update(button) {
  const userId = button.dataset.userId;
  const role = button.dataset.role;

  if (role === "STUDENT") {
    try {
      const response = await fetch(`http://localhost:8080/student/${userId}`);

      if (!response.ok) {
        throw new Error("Error getting all the student data");
      }
      const data = await response.json();

      const modal = document.getElementById("modalStudentUpdate");
      modal.style.display = "block";

      const modalStudentUpdate = document.getElementById("modalStudentUpdate");
      const closeUpdateStudentBtn =
        document.getElementById("studentUpdateClose");
      closeUpdateStudentBtn.onclick = function () {
        modalStudentUpdate.style.display = "none";
      };

      const container = document.getElementById(
        "studentUpdateProfileFormContainer"
      );

      const newForm = document.createElement("form");
      newForm.id = "studentUpdateProfileForm";
      newForm.classList = "createUserForm";

      newForm.innerHTML = `
            <div class="form-group">
          <label for="studentUpdateEmail">Update Email:</label>
          <input type="email" id="studentUpdateEmail" placeholder="${
            data.user.email || ""
          }" />
        </div>

        <div class="form-group">
          <label for="studentUpdateUsername">Update Username:</label>
          <input type="text" id="studentUpdateUsername" placeholder="${
            data.user.username || ""
          }" />
        </div>

        <div class="form-group">
          <label for="studentUpdatePassword">Update Password:</label>
          <input type="password" id="studentUpdatePassword" placeholder="${
            data.user.password
          }" />
        </div>

        <div class="form-group">
          <label for="studentUpdateName">Update Name:</label>
          <input type="text" id="studentUpdateName" placeholder="${
            data.user.name || ""
          }" />
        </div>

        <div class="form-group">
          <label for="studentUpdatePhone">Update Phone Number:</label>
          <input type="text" id="studentUpdatePhone" placeholder="${
            data.user.phone || ""
          }" />
        </div>

        <div class="form-group">
          <label for="studentUpdatePhotoUrl">Update Photo URL:</label>
          <input type="text" id="studentUpdatePhotoUrl" placeholder="${
            data.photo || ""
          }" />
        </div>

        <div class="form-group">
          <label for="yearUpdate">Update Year:</label>
          <input type="text" id="yearUpdate" placeholder="${data.year || ""}" />
        </div>

        <div class="form-group">
          <label for="studentUpdateDepartmentId">Update Department ID:</label>
          <input type="text" id="studentUpdateDepartmentId" placeholder="${
            data.department.id || ""
          }" />
        </div>

        <button type="submit">Update</button>
      `;
      container.innerHTML = ""; // Clear previous form if any
      container.appendChild(newForm);

      newForm.addEventListener("submit", async (event) => {
        event.preventDefault(); // Prevent form from refreshing the page

        const studentEmail =
          document.getElementById("studentUpdateEmail").value;
        const username = document.getElementById("studentUpdateUsername").value;
        const password = document.getElementById("studentUpdatePassword").value;
        const studentName = document.getElementById("studentUpdateName").value;
        const studentPhone =
          document.getElementById("studentUpdatePhone").value;
        const photoUrl = document.getElementById("studentUpdatePhotoUrl").value;
        const year = document.getElementById("yearUpdate").value;
        const departmentId = document.getElementById(
          "studentUpdateDepartmentId"
        ).value;

        const updatedUser = {};
        const updatedStudent = {};

        // Update only if fields are filled
        addIfNotEmpty(updatedUser, "username", username);
        addIfNotEmpty(updatedUser, "password", password);
        addIfNotEmpty(updatedUser, "role", "STUDENT");
        addIfNotEmpty(updatedUser, "name", studentName);
        addIfNotEmpty(updatedUser, "email", studentEmail);
        addIfNotEmpty(updatedUser, "phone", studentPhone);

        addIfNotEmpty(updatedStudent, "photo", photoUrl);
        addIfNotEmpty(updatedStudent, "departmentId", departmentId);
        addIfNotEmpty(updatedStudent, "year", year);

        console.log(updatedUser);
        console.log(updatedStudent);

        const updateResponse = await fetch(
          `http://localhost:8080/users/${userId}`,
          {
            method: "PATCH",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedUser),
          }
        );

        const updateStudent = await fetch(
          `http://localhost:8080/student/${userId}`,
          {
            method: "PATCH",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedStudent),
          }
        );
        if (updateStudent.ok && updateResponse.ok)
          window.alert("update successful");
        modal.style.display = "none";
        await getAllStudents();
      });
    } catch (error) {
      window.alert(error);
    }
  }

  if (role === "FACULTY") {
    try {
      const response = await fetch(`http://localhost:8080/faculty/${userId}`);

      if (!response.ok) {
        throw new Error("Error getting all the student data");
      }
      const data = await response.json();

      const modal = document.getElementById("modalFacultyUpdate");
      modal.style.display = "block";

      const modalFacultyUpdate = document.getElementById("modalFacultyUpdate");
      const closeUpdateStudentBtn =
        document.getElementById("facultyUpdateClose");
      closeUpdateStudentBtn.onclick = function () {
        modalFacultyUpdate.style.display = "none";
      };

      const container = document.getElementById(
        "facultyUpdateProfileFormContainer"
      );

      const newForm = document.createElement("form");
      newForm.id = "facultyUpdateProfileForm";
      newForm.classList = "createUserForm";

      newForm.innerHTML = `
            <div class="form-group">
          <label for="facultyUpdateEmail">Update Email:</label>
          <input type="email" id="facultyUpdateEmail" placeholder="${
            data.user.email || ""
          }" />
        </div>

        <div class="form-group">
          <label for="facultyUpdateUsername">Update Username:</label>
          <input type="text" id="facultyUpdateUsername" placeholder="${
            data.user.username || ""
          }" />
        </div>

        <div class="form-group">
          <label for="facultyUpdatePassword">Update Password:</label>
          <input type="password" id="facultyUpdatePassword" placeholder="${
            data.user.password
          }" />
        </div>

        <div class="form-group">
          <label for="facultyUpdateName">Update Name:</label>
          <input type="text" id="facultyUpdateName" placeholder="${
            data.user.name || ""
          }" />
        </div>

        <div class="form-group">
          <label for="facultyUpdatePhone">Update Phone Number:</label>
          <input type="text" id="facultyUpdatePhone" placeholder="${
            data.user.phone || ""
          }" />
        </div>

        <div class="form-group">
          <label for="facultyUpdatePhotoUrl">Update Photo URL:</label>
          <input type="text" id="facultyUpdatePhotoUrl" placeholder="${
            data.photo || ""
          }" />
        </div>

        <div class="form-group">
          <label for="facultyUpdateDepartmentId">Update Department ID:</label>
          <input type="text" id="facultyUpdateDepartmentId" placeholder="${
            data.department.id || ""
          }" />
        </div>

        <div class="form-group">
          <label for="facultyOfficeHours">Update Office Hours:</label>
          <input type="text" id="facultyOfficeHours" placeholder="${
            data.officeHours || ""
          }" />
        </div>

        <button type="submit">Update</button>
      `;

      container.innerHTML = "";
      container.appendChild(newForm);

      newForm.addEventListener("submit", async (event) => {
        event.preventDefault(); // Prevent form from refreshing the page

        const studentEmail =
          document.getElementById("facultyUpdateEmail").value;
        const username = document.getElementById("facultyUpdateUsername").value;
        const password = document.getElementById("facultyUpdatePassword").value;
        const studentName = document.getElementById("facultyUpdateName").value;
        const studentPhone =
          document.getElementById("facultyUpdatePhone").value;
        const photoUrl = document.getElementById("facultyUpdatePhotoUrl").value;
        const departmentId = document.getElementById(
          "facultyUpdateDepartmentId"
        ).value;
        const officeHours = document.getElementById("facultyOfficeHours").value;

        const updatedUser = {};
        const updatedFaculty = {};

        // Update only if fields are filled
        addIfNotEmpty(updatedUser, "username", username);
        addIfNotEmpty(updatedUser, "password", password);
        addIfNotEmpty(updatedUser, "role", "STUDENT");
        addIfNotEmpty(updatedUser, "name", studentName);
        addIfNotEmpty(updatedUser, "email", studentEmail);
        addIfNotEmpty(updatedUser, "phone", studentPhone);

        addIfNotEmpty(updatedFaculty, "photo", photoUrl);
        addIfNotEmpty(updatedFaculty, "departmentId", departmentId);
        addIfNotEmpty(updatedFaculty, "officeHours", officeHours);

        console.log(updatedUser);
        console.log(updatedFaculty);

        const updateResponse = await fetch(
          `http://localhost:8080/users/${userId}`,
          {
            method: "PATCH",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedUser),
          }
        );

        const updateFaculty = await fetch(
          `http://localhost:8080/faculty/${userId}`,
          {
            method: "PATCH",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedFaculty),
          }
        );
        if (updatedFaculty.ok && updateResponse.ok)
          window.alert("update successful");
        modal.style.display = "none";
        await getAllFaculties();
      });
    } catch (error) {
      window.alert(error);
    }
  }
}

async function deleteEntry(button) {
  const userId = button.dataset.userId;
  const role = button.dataset.role;
  if (role === "STUDENT") {
    try {
      const modalDelete = document.getElementById("modalDelete");
      console.log(modalDelete);
      modalDelete.style.display = "block";
      document.getElementById("proceed").onclick = async function () {
        let response = await fetch(`http://localhost:8080/student/${userId}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Error while deleting student data");
        }

        response = await fetch(`http://localhost:8080/users/${userId}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Error while deleting user data");
        }

        window.alert(`Student with ID ${userId} deleted successfully.`);
        modalDelete.style.display = "none";
      };
      document.getElementById("cancel").onclick = function () {
        modalDelete.style.display = "none";
      };
      await getAllStudents();
    } catch (error) {
      console.error(error);
      alert("An error occurred while deleting. Please try again.");
    }
  }
  if (role === "FACULTY") {
    try {
      const modalDelete = document.getElementById("modalDelete");
      console.log(modalDelete);
      modalDelete.style.display = "block";
      document.getElementById("proceed").onclick = async function () {
        let response = await fetch(`http://localhost:8080/faculty/${userId}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Error while deleting student data");
        }

        response = await fetch(`http://localhost:8080/users/${userId}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Error while deleting user data");
        }

        window.alert(`Student with ID ${userId} deleted successfully.`);
        modalDelete.style.display = "none";
      };
      document.getElementById("cancel").onclick = function () {
        modalDelete.style.display = "none";
      };
      await getAllFaculties();
    } catch (error) {
      console.error(error);
      alert("An error occurred while deleting. Please try again.");
    }
  }
}

async function getAllCourses() {
  try {
    const response = await fetch(`http://localhost:8080/course`);
    if (!response.ok) {
      throw new Error("Error while fetching courses");
    }

    const data = await response.json();

    const container = document.getElementById("courseList");
    const courseList = container.querySelector(".studentCourseList");
    courseList.innerHTML = "";

    data.forEach((course) => {
      const courseEntry = document.createElement("div");
      courseEntry.className = "studentDetails";

      courseEntry.innerHTML = `
      <span>Course ID: ${course.id}</span>
      <span>Department ID: ${course.department.id}</span>
      <span>Faculty: ${course.faculty.userId}</span>
      <span>Title: ${course.title}</span>
      <span>Description: ${course.description}</span>
      <span>Department Name: ${course.department.name}</span>
      <button class="updateBtn" data-course-id="${course.id}" onclick="updateCourse(this)">Update</button>
      <button class="deleteBtn" data-course-id="${course.id}" onclick="deleteCourse(this)">Delete</button> `;

      courseList.appendChild(courseEntry);
    });
  } catch (error) {
    window.alert(error);
  }
}

async function getAllDepartments() {
  try {
    const response = await fetch(`http://localhost:8080/department`);
    if (!response.ok) {
      throw new Error("Error while fetching department");
    }

    const data = await response.json();

    const container = document.getElementById("departmentList");
    const departmentList = container.querySelector(".studentCourseList");
    departmentList.innerHTML = "";

    data.forEach((department) => {
      const departmentEntry = document.createElement("div");
      departmentEntry.className = "studentDetails";

      departmentEntry.innerHTML = `
      <span>Department ID: ${department.id}</span>
      <span>Name: ${department.name}</span>
      <span>Description: ${department.description}</span>
      <button class="updateBtn" data-dept-id="${department.id}" onclick="updateDepartment(this)">Update</button>
      <button class="deleteBtn" data-dept-id="${department.id}" onclick="deleteDepartment(this)">Delete</button> `;

      departmentList.appendChild(departmentEntry);
    });
  } catch (error) {
    window.alert(error);
  }
}

document
  .getElementById("departmentForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const departmentName = document.getElementById("departmentName").value;
    const departmentDescription = document.getElementById(
      "departmentDecription"
    ).value;

    const newDepartment = {
      name: departmentName,
      description: departmentDescription,
    };

    const newDepartmentJson = JSON.stringify(newDepartment);

    const response = await fetch("http://localhost:8080/department", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: newDepartmentJson,
    });

    if (!response.ok) {
      window.alert("Not able to create department");
      return;
    }
    window.alert("Department created Successfully");
    getAllDepartments();
  });

document
  .getElementById("courseForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const courseTitle = document.getElementById("courseTitle").value;
    const courseDescription =
      document.getElementById("courseDescription").value;
    const departmentID = document.getElementById("departmentId").value;
    const facultyID = document.getElementById("facultyId").value;

    const newCourse = {
      title: courseTitle,
      description: courseDescription,
      departmentId: departmentID,
      facultyId: facultyID,
    };

    const newCourseJson = JSON.stringify(newCourse);

    const response = await fetch("http://localhost:8080/course/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: newCourseJson,
    });

    if (!response.ok) {
      window.alert("Not able to create course");
      return;
    }
    window.alert("Course created Successfully");
    getAllCourses();
  });

async function updateCourse(button) {
  const courseId = button.dataset.courseId;

  const response = await fetch(`http://localhost:8080/course/${courseId}`);
  if (!response.ok) {
    throw new Error("Error while fetching course data");
  }

  const data = await response.json();

  const modal = document.getElementById("modalCourseUpdate");
  modal.style.display = "block";

  const closeBtn = document.getElementById("courseUpdateClose");
  closeBtn.onclick = function () {
    modal.style.display = "none";
  };

  const container = document.getElementById("CourseUpdateProfileFormContainer");
  const newForm = document.createElement("form");
  newForm.id = "courseUpdateForm";
  newForm.classList = "createUserForm";

  newForm.innerHTML = `
    <div class="form-group">
      <label for="courseUpdateTitle">Update Title:</label>
      <input type="text" id="courseUpdateTitle" placeholder="${
        data.title || ""
      }" />
    </div>

    <div class="form-group">
      <label for="courseUpdateDescription">Update Description:</label>
      <input type="text" id="courseUpdateDescription" placeholder="${
        data.description || ""
      }" />
    </div>

    <div class="form-group">
      <label for="courseUpdateDepartment">Update Department ID:</label>
      <input type="text" id="courseUpdateDepartment" placeholder="${
        data.department.id || ""
      }" />
    </div>

    <div class="form-group">
      <label for="courseUpdateFaculty">Update Faculty ID:</label>
      <input type="text" id="courseUpdateFaculty" placeholder="${
        data.faculty.userId || ""
      }" />
    </div>

    <button type="submit">Update</button>
  `;

  container.innerHTML = "";
  container.appendChild(newForm);

  newForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const updatedTitle = document.getElementById("courseUpdateTitle").value;
    const updatedDescription = document.getElementById(
      "courseUpdateDescription"
    ).value;
    const updatedDepartmentId = document.getElementById(
      "courseUpdateDepartment"
    ).value;
    const updatedFacultyId = document.getElementById(
      "courseUpdateFaculty"
    ).value;

    const updatedCourse = {};

    addIfNotEmpty(updatedCourse, "title", updatedTitle);
    addIfNotEmpty(updatedCourse, "description", updatedDescription);
    addIfNotEmpty(updatedCourse, "departmentId", updatedDepartmentId);
    addIfNotEmpty(updatedCourse, "facultyId", updatedFacultyId);

    const response = await fetch(`http://localhost:8080/course/${courseId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedCourse),
    });

    if (response.ok) window.alert("update successful");
    modal.style.display = "none";
    await getAllCourses();
  });
}

async function updateDepartment(button) {
  const departmentId = button.dataset.deptId;

  const response = await fetch(
    `http://localhost:8080/department/${departmentId}`
  );
  if (!response.ok) {
    throw new Error("Error while fetching department data");
  }

  const data = await response.json();

  const modal = document.getElementById("modalDepartmentUpdate");
  modal.style.display = "block";

  const closeBtn = document.getElementById("departmentUpdateClose");
  closeBtn.onclick = function () {
    modal.style.display = "none";
  };

  const container = document.getElementById(
    "departmentUpdateProfileFormContainer"
  );
  const newForm = document.createElement("form");
  newForm.id = "departmentUpdateForm";
  newForm.classList = "createUserForm";

  newForm.innerHTML = `
    <div class="form-group">
      <label for="departmentUpdateName">Update Name:</label>
      <input type="text" id="departmentUpdateName" placeholder="${
        data.name || ""
      }" />
    </div>

    <div class="form-group">
      <label for="departmentUpdateDescription">Update Description:</label>
      <input type="text" id="departmentUpdateDescription" placeholder="${
        data.description || ""
      }" />
    </div>
    <button type="submit">Update</button>
  `;

  container.innerHTML = "";
  container.appendChild(newForm);

  newForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const updatedName = document.getElementById("departmentUpdateName").value;
    const updatedDescription = document.getElementById(
      "departmentUpdateDescription"
    ).value;

    const updatedDepartment = {};

    addIfNotEmpty(updatedDepartment, "name", updatedName);
    addIfNotEmpty(updatedDepartment, "description", updatedDescription);

    const response = await fetch(
      `http://localhost:8080/department/${departmentId}`,
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedDepartment),
      }
    );

    if (response.ok) window.alert("update successful");
    modal.style.display = "none";
    await getAllDepartments();
  });
}

async function deleteCourse(button) {
  const courseId = button.dataset.courseId;

  const modalDelete = document.getElementById("modalDelete");
  modalDelete.style.display = "block";

  document.getElementById("proceed").onclick = async function () {
    let response = await fetch(`http://localhost:8080/course/${courseId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      window.alert("Error while deleting");
      return;
    }

    window.alert("Successfully deleted");
    modalDelete.style.display = "none"; // Corrected this line to hide the modal
    await getAllCourses();
  };

  document.getElementById("cancel").onclick = function () {
    modalDelete.style.display = "none";
  };
}

document
  .getElementById("searchForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const modal = document.getElementById("modalDashboard");
    modal.style.display = "block";

    const enrollmentGraph = document.getElementById("enrollmentAndLoadWrapper");
    enrollmentGraph.innerHTML = "";

    const newEnrollmentGraph = document.createElement("div");
    newEnrollmentGraph.id = "enrollmentTrends";

    const response1 = await fetch(
      "http://localhost:8080/dashboard/enrollment-trends"
    );
    if (!response1.ok) throw window.alert("Error while fetching trends");

    const data = await response1.json();

    const enrollmentXValues = [];
    const enrollmentYValues = [];
    data.forEach((entry) => {
      enrollmentXValues.push(entry.year);
      enrollmentYValues.push(entry.count);
    });

    newEnrollmentGraph.innerHTML =
      '<canvas class="myChart" id="enrollmentTrendChart" style="width:50%;max-width:50%"></canvas>';

    enrollmentGraph.appendChild(newEnrollmentGraph);

    const canvas = document.getElementById("enrollmentTrendChart");
    const ctx = canvas.getContext("2d");

    const enrollmentYMax = Math.max(...enrollmentYValues) + 1; // Add 1 for a little space above the highest bar
    new Chart(ctx, {
      type: "bar",
      data: {
        labels: enrollmentXValues,
        datasets: [
          {
            backgroundColor: randomColors(enrollmentYValues.length),
            data: enrollmentYValues,
            barThickness: 50, // Fixed width for bars
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            barPercentage: 0.5,
            categoryPercentage: 0.8,
          },
          y: {
            beginAtZero: true,
          },
        },
        plugins: {
          legend: {
            display: false,
          },
          title: {
            display: true,
            text: "Enrollment Trends",
          },
        },
      },
    });

    const courseLoadGraph = document.createElement("div");
    courseLoadGraph.id = "courseLoads";

    const response2 = await fetch(
      "http://localhost:8080/dashboard/faculty-course-loads"
    );
    if (!response2.ok) throw window.alert("Error while fetching trends");

    const data2 = await response2.json();

    const courseLoadXValues = [];
    const courseLoadYValues = [];
    data2.forEach((entry) => {
      if (entry.courseCount > 0) {
        courseLoadXValues.push(entry.facultyName);
        courseLoadYValues.push(entry.courseCount);
      }
    });

    courseLoadGraph.innerHTML =
      '<canvas class="myChart" id="courseLoadChart" style="width:50%;max-width:50%"></canvas>';

    enrollmentGraph.appendChild(courseLoadGraph);

    const canvas2 = document.getElementById("courseLoadChart");
    const ctx2 = canvas2.getContext("2d");

    const courseLoadYMax = Math.max(...courseLoadYValues) + 1; // Add 1 for a little space above the highest bar
    new Chart(ctx2, {
      type: "bar",
      data: {
        labels: courseLoadXValues,
        datasets: [
          {
            backgroundColor: randomColors(courseLoadYValues.length),
            data: courseLoadYValues,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            min: 0,
            max: courseLoadYMax,
          },
        },
        plugins: {
          legend: { display: false },
          title: {
            display: true,
            text: "Course Load Trends",
          },
        },
      },
    });

    document.getElementById("dashboardClose").onclick = function () {
      modal.style.display = "none";
    };
  });

function randomColors(count) {
  const colors = [];
  for (let i = 0; i < count; i++) {
    colors.push(`hsl(${Math.random() * 360}, 70%, 50%)`); // Random HSL color with saturation
  }
  return colors;
}
