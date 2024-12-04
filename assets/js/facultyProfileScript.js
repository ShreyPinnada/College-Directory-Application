const profilePhoto = document.getElementsByTagName("img");
const facultyName = document.getElementById("profileName");
const facultyUserId = document.getElementById("facultyUserId");
const facultyDeparment = document.getElementById("facultyDepartmentName");
const officeHours = document.getElementById("facultyOfficeHours");

const userId = localStorage.getItem("userId");
const userName = localStorage.getItem("nameOfUser");

async function getFacultyDetails() {
  try {
    const response = await fetch(`http://localhost:8080/faculty/${userId}`);
    if (!response.ok) {
      throw new Error("Faculty not found");
    }

    const data = await response.json();
    profilePhoto[0].src = data.photo;
    facultyName.textContent = userName;
    facultyUserId.textContent = `User ID: ${userId}`;
    facultyDeparment.textContent = `Department: ${data.department.name}`;
    officeHours.textContent = `Office Hours: ${data.officeHours}`;
  } catch (error) {
    console.error(error);
  }
}

getFacultyDetails();

async function getCoursesHandledByFaculty() {
  try {
    const response = await fetch(
      `http://localhost:8080/course/faculty/${userId}`
    );
    if (!response.ok) {
      throw new Error("Course not found");
    }

    const data = await response.json();

    displayCouses(data);
  } catch (error) {
    console.error(error);
  }
}

function displayCouses(coursesList) {
  const container = document.getElementById("courseContainer");

  container.innerHTML = "";

  coursesList.forEach((course) => {
    const newDiv = document.createElement("div");

    newDiv.classList = "courseSection";

    newDiv.innerHTML = `
        <label>${course.title}</label>
            <div class="studentCourseList">
              <span class="courseDescription">${course.description}</span>
            </div>
        `;
    container.appendChild(newDiv);
  });
}

getCoursesHandledByFaculty();

document
  .getElementById("updateFacultyForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();
    let newofficeHours =
      document.getElementById("updateFacultyOfficeHours").value || null;
    let newEmail = document.getElementById("updateFacultyEmail").value || null;
    let newphoneNumber =
      document.getElementById("updateFacultyPhoneNumber").value || null;

    const userPatch = {};
    if (newEmail) userPatch.email = newEmail;
    if (newphoneNumber) userPatch.phone = newphoneNumber;

    const userPatchJson = JSON.stringify(userPatch);

    const optionsUserPatch = {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: userPatchJson,
    };

    await fetch(`http://localhost:8080/users/${userId}`, optionsUserPatch)
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

    const facultyPatch = {};
    if (newofficeHours) facultyPatch.officeHours = newofficeHours;

    const facultyPatchJson = JSON.stringify(facultyPatch);

    const optionsFacultyPatch = {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: facultyPatchJson,
    };

    await fetch(`http://localhost:8080/faculty/${userId}`, optionsFacultyPatch)
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
    await getFacultyDetails();
    document.getElementById("updateFacultyOfficeHours").value = "";
    document.getElementById("updateFacultyEmail").value = "";
    document.getElementById("updateFacultyPhoneNumber").value = "";
  });
