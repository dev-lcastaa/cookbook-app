// Add event listeners to the buttons
document.addEventListener("DOMContentLoaded", function () {
    var addCookBookButton = document.getElementById("addCookBookButton");
    var removeCookBookButton = document.getElementById("removeCookBookButton");

    if (addCookBookButton) {
        addCookBookButton.addEventListener("click", function () {
            addCookBook();
        });
    }
    if (removeCookBookButton) {
        removeCookBookButton.addEventListener("click", function () {
            removeCookBook();
        });
    }

});


// Function to retrieve and display cookbooks from sessionStorage
function displayCookbooks() {
    const cookbookList = document.getElementById("cookbookList");
    cookbookList.innerHTML = ''; // Clear existing list

    // Retrieve cookbooks from sessionStorage
    const cookbooks = JSON.parse(sessionStorage.getItem('cookbooks')) || [];

    if (cookbooks.length === 0) {
        // If no cookbooks found, display a message
        cookbookList.innerHTML = '<li>No cookbooks found.</li>';
    } else {
        // Create list items for each cookbook
        cookbooks.forEach(cookbook => {
            const listItem = document.createElement("li");
            listItem.textContent = cookbook;
            cookbookList.appendChild(listItem);
        });
    }
}

// Function to add cook books from the user
function addCookBook(){
    var name = document.getElementById("cookbookName").value;
    var type = document.getElementById("cookbookType").value;
    var token = sessionStorage.getItem("token");

    var data = {
        name: name,
        type: type
    };

    console.log(data);

    fetch("/api/v1/cookbook/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Login failed.");
        }
    })
    .then(function (data) {
        // Handle successful login
        console.log("Login successful:", data);
        window.location.href = "/v1/account";
    })
    .catch(function (error) {
        // Handle errors
        console.error("Login failed:", error);
    });

}

// Function to remove cook books from the user
function removeCookBook(){}

// Call the function to display cookbooks when the page loads
window.addEventListener("load", displayCookbooks);