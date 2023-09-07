// Function to add Cook book
async function addCookBook() {
    const name = document.getElementById("cookbookName").value;
    const userDataJSON = sessionStorage.getItem("user");
    const token = sessionStorage.getItem("token");

    if (userDataJSON) {
        const userData = JSON.parse(userDataJSON);
        const userId = userData.userId;

        const data = {
            name: name,
            userId: userId
        };

        console.log(data);

        try {
            const response = await fetch("/api/v1/cookbook", {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                sessionStorage.removeItem("cookbooks")
                const addedCookbook = await response.json();
                console.log("CookBook added successfully:", addedCookbook);
            } else {
                throw new Error("Add CookBook failed.");
            }
        } catch (error) {
            console.error("Add CookBook failed:", error.message);
        }
    } else {
        console.log("User data not found in sessionStorage.");
    }

    window.location.href = "/v1/main";
}

// Function to delete Cook book
async function deleteCookBook(cookBookId)  {
    console.log("Deleting cookbook with ID: " + cookBookId);
    const token = sessionStorage.getItem("token");
    try {
         const response = await fetch(`/api/v1/cookbook?id=${cookBookId}`, {
             method: "DELETE",
             headers: {
                 "Authorization": `Bearer ${token}`,
                 "Content-Type": "application/json"
             }
         });

         if (response.ok) {
             console.log("CookBook removed successfully:");
             window.location.href = `/v1/cookbook?cookBookId=${cookBookId}`;
         } else {
             throw new Error("Removed CookBook failed.");
         }
    } catch (error) {
         console.error("Remove CookBook failed:", error.message);
    }
}


// Function to display Cook books with a delete option
function displayDeleteCookBooks() {
    // Retrieve the JSON string from Session Storage
    var storedList = sessionStorage.getItem("cookbooks");

    // Check if the JSON string exists
    if (storedList) {
        // Parse the JSON string into a JavaScript object
        var parsedList = JSON.parse(storedList);

        // Access the list container
        var listContainer = document.getElementById('listOfCookBooksToDelete');

        // Clear any existing content in the list container
        listContainer.innerHTML = '';

        // Create a variable to store the selected cookbook ID
        var selectedCookbookId = null;

        parsedList.forEach(function (cookbook) {
            // Create a list item for each cookbook
            var listItem = document.createElement('li');

            // Add a class to the list item
            listItem.classList.add('list-group-item-delete');

            // Create an anchor element for the cookbook with a link
            var cookbookLink = document.createElement('a');
            cookbookLink.classList.add('a-deleteBookList');
            cookbookLink.textContent = cookbook.name;
            cookbookLink.href = "#"; // Set a dummy href, as we don't need a real URL

            // Add a click event listener to the anchor element to select the cookbook
            cookbookLink.addEventListener("click", function () {
                selectedCookbookId = cookbook.id;
            });

            // Append the anchor element to the list item
            listItem.appendChild(cookbookLink);

            // Append the list item to the list container
            listContainer.appendChild(listItem);
        });

        // Create a delete button and add a click event listener to trigger deletion
        var deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete Selected Cookbook';
        deleteButton.addEventListener("click", function () {
            if (selectedCookbookId !== null) {
                // Call a function to handle the deletion of the selected cookbook
                deleteCookBook(selectedCookbookId);
            } else {
                alert("Please select a cookbook to delete.");
            }
        });

        // Append the delete button to the list container
        listContainer.appendChild(deleteButton);
    }
}

// Function to get a list of Cook books
async function getListOfCookBooks() {

    const userDataJSON = sessionStorage.getItem("user");
    const token = sessionStorage.getItem("token");
    const userData = JSON.parse(userDataJSON);
    const userId = userData.userId;



    try {
        const response = await fetch(`/api/v1/cookbook?userId=${userId}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const cookbooks = await response.json();
            console.log(cookbooks);
            sessionStorage.setItem("cookbooks", JSON.stringify(cookbooks)); // Store as JSON string
            displayListOfCookBooks();
        } else {
            throw new Error(`Failed to fetch recipes. HTTP status: ${response.status}`);
        }
    } catch (error) {
        throw new Error(`Error fetching recipes: ${error.message}`);
    }
}

// Function to display Cook books
function displayListOfCookBooks() {

    // Retrieve the JSON string from Session Storage
    var storedList = sessionStorage.getItem("cookbooks");


    // Check if the JSON string exists
    if (storedList) {

        // Parse the JSON string into a JavaScript object
        var parsedList = JSON.parse(storedList);


        // Access the list container
        var listContainer = document.getElementById('listOfCookBooks');


        // Clear any existing content in the list container
        listContainer.innerHTML = '';


        parsedList.forEach(function (cookbook) {

            // Create a list item for each cookbook
            var listItem = document.createElement('li');

            // Add a class to the list item
            listItem.classList.add('list-group-item');

            // Create an anchor element for the cookbook with a link
            var cookbookLink = document.createElement('a');
            cookbookLink.classList.add('a-cookBookList');

            cookbookLink.textContent = cookbook.name;
            cookbookLink.href = `/v1/cookbook?cookBookId=${cookbook.id}`;

            // Append the anchor element to the list item
            listItem.appendChild(cookbookLink);

            // Append the list item to the list container
            listContainer.appendChild(listItem);

        });
    }
}

// Adds event listeners for page
function addEventListeners() {
    const addCookBookButton = document.getElementById("addCookBook");
    const deleteCookBookButton = document.getElementById("deleteCookBookModalButton");

    if (addCookBookButton) {
        addCookBookButton.addEventListener("click", addCookBook);
    }
    if (deleteCookBookButton) {
        deleteCookBookButton.addEventListener("click", displayDeleteCookBooks);
    }
}

// Add event listeners when the DOM content is loaded
document.addEventListener("DOMContentLoaded", function () {
    // Your event listeners and function calls here
    addEventListeners();
    getListOfCookBooks();
    displayDeleteCookBooks();

});
