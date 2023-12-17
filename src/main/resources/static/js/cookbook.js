// Function to add Recipes
async function addRecipe() {
    const name = document.getElementById("recipeName").value;
    const cookBookId = dataFromServer.cookBookId;
    const ingredients = document.getElementById("ingredients").value;
    const steps = document.getElementById("steps").value;
    const token = sessionStorage.getItem("token");

    const data = {
        name: name,
        cookBookId: cookBookId,
        ingredients: ingredients,
        steps: steps
    };

    try {
        const response = await fetch("/v1/api/recipe", {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            console.log("Recipe added successfully");

            // Clear input fields
            document.getElementById("recipeName").value = "";
            document.getElementById("ingredients").value = "";
            document.getElementById("steps").value = "";
        } else {
            throw new Error("Add Recipe failed.");
        }
    } catch (error) {
        console.error("Add Recipe failed:", error.message);
    }

    // Redirect to another page after adding the recipe
    window.location.href = `/ui/cookbook?cookBookId=${cookBookId}`;
}

// Function to delete Recipes
async function deleteRecipe(recipeId) {
    console.log("Deleting recipe with ID: " + recipeId);
    const token = sessionStorage.getItem("token");
    try {
        const response = await fetch(`/v1/api/recipe?recipeId=${recipeId}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.status === 204) {
            console.log("Recipe removed successfully:");
            window.location.href = "/ui/cookbook?cookBookId=" + dataFromServer.cookBookId;
        } else {
            throw new Error("Removed Recipe failed.");
        }
    } catch (error) {
        console.error("Remove Recipe failed:", error.message);
    }
}

// Function to get a list of Recipes
async function getListOfRecipes() {
    const token = sessionStorage.getItem("token");

    try {
        const response = await fetch(`/v1/api/recipe?cookBookId=${dataFromServer.cookBookId}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const recipes = await response.json();
            console.log(recipes);
            sessionStorage.setItem("recipes", JSON.stringify(recipes));
            displayListOfRecipes();
        } else {
            throw new Error(`Failed to fetch recipes. HTTP status: ${response.status}`);
        }
    } catch (error) {
        throw new Error(`Error fetching recipes: ${error.message}`);
    }
}

// Function to populate the list of recipes and set up click event listeners
function displayListOfRecipes() {
    const listContainer = document.getElementById('listOfRecipes');

    // Retrieve the JSON string from Session Storage
    var storedList = sessionStorage.getItem("recipes");

    // Check if the JSON string exists
    if (storedList) {
        // Parse the JSON string into a JavaScript object
        var parsedList = JSON.parse(storedList);

        // Clear any existing content in the list container
        listContainer.innerHTML = '';

        parsedList.forEach(function (recipe) {
            // Create a list item for each recipe
            var listItem = document.createElement('li');

            // Add a class to the list item
            listItem.classList.add('list-group-item');

            // Create a clickable element for the cookbook with a link
            var recipeLink = document.createElement('a');
            recipeLink.classList.add('a-cookBookList');

            recipeLink.textContent = recipe.name;
            recipeLink.href = '#'; // Set a dummy href, as we don't need a real URL

            // Add a click event listener to open the viewRecipeModal
            recipeLink.addEventListener('click', function () {
                displayRecipeDetails(recipe);
            });

            // Append the anchor element to the list item
            listItem.appendChild(recipeLink);

            // Append the list item to the list container
            listContainer.appendChild(listItem);
        });
    }
}

// Add event listeners when the DOM content is loaded
document.addEventListener("DOMContentLoaded", function () {
    // Your event listeners and function calls here
    addEventListeners();
    getListOfRecipes();
    displayListOfRecipes();
});


function displayListOfDeleteRecipes() {
    // Retrieve the JSON string from Session Storage
    var storedList = sessionStorage.getItem("recipes");

    // Check if the JSON string exists
    if (storedList) {
        // Parse the JSON string into a JavaScript object
        var parsedList = JSON.parse(storedList);

        // Access the list container
        var listContainer = document.getElementById('listOfRecipesToDelete');

        // Clear any existing content in the list container
        listContainer.innerHTML = '';

        // Create a variable to store the selected recipe ID
        var selectedRecipeId = null;

        parsedList.forEach(function (recipe) {
            // Create a list item for each recipe
            var listItem = document.createElement('li');

            // Add a class to the list item
            listItem.classList.add('list-group-item-delete');

            // Create an anchor element for the recipe with a link
            var recipeLink = document.createElement('a');
            recipeLink.classList.add('a-deleteBookList');
            recipeLink.textContent = recipe.name;
            recipeLink.href = "#"; // Set a dummy href, as we don't need a real URL

            // Add a click event listener to the anchor element to select the recipe
            recipeLink.addEventListener("click", function () {
                selectedRecipeId = recipe.id;
            });

            // Append the anchor element to the list item
            listItem.appendChild(recipeLink);

            // Append the list item to the list container
            listContainer.appendChild(listItem);
        });

        // Create a delete button and add a click event listener to trigger deletion
        var deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete Selected Recipe';
        deleteButton.addEventListener("click", function () {
            if (selectedRecipeId !== null) {
                // Call a function to handle the deletion of the selected recipe
                deleteRecipe(selectedRecipeId);
            } else {
                alert("Please select a Recipe to delete.");
            }
        });

        // Append the delete button to the list container
        listContainer.appendChild(deleteButton);
    }
}

// Function to display the details of a recipe in the viewRecipeModal
function displayRecipeDetails(recipe) {
    const viewRecipeModal = document.getElementById("viewRecipeModal");
    const modalBody = viewRecipeModal.querySelector(".modal-body");

    // Split the ingredients and steps by line breaks
    const ingredientsList = recipe.ingredients.split('\n').map(item => `<div>${item}</div>`).join('');
    const stepsList = recipe.steps.split('\n').map(item => `<div>${item}</div>`).join('');

    // Populate the modal with the recipe details
    modalBody.innerHTML = `
        <strong>Recipe Name:</strong><br>${recipe.name}<br>
        <strong>Ingredients:</strong><br>${ingredientsList}<br>
        <strong>Steps:</strong><br>${stepsList}
    `;

    // Show the viewRecipeModal
    new bootstrap.Modal(viewRecipeModal).show();
}

// Adds event listeners for page
function addEventListeners() {
    const addRecipeButton = document.getElementById("addRecipe");
    const deleteRecipeButton = document.getElementById("deleteRecipeModalButton");

    if (addRecipeButton) {
        addRecipeButton.addEventListener("click", addRecipe);
    }
    if (deleteRecipeButton) {
        deleteRecipeButton.addEventListener("click", displayListOfDeleteRecipes);
    }
}

// Add event listeners when the DOM content is loaded
document.addEventListener("DOMContentLoaded", function () {
    // Your event listeners and function calls here
    addEventListeners();
    getListOfRecipes();
    displayListOfDeleteRecipes();
});
