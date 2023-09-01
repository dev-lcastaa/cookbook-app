// Function to add event listeners to buttons
function addEventListeners() {
    const addCookBookButton = document.getElementById("addCookBookButton");
    const removeCookBookButton = document.getElementById("removeCookBookButton");

    if (addCookBookButton) {
        addCookBookButton.addEventListener("click", addCookBook);
    }
    if (removeCookBookButton) {
        removeCookBookButton.addEventListener("click", removeCookBook);
    }
}

// Function to fetch cookbooks from the server
async function fetchCookbooks(userId, token) {
    try {
        const response = await fetch(`/api/v1/cookbook?userId=${userId}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const fetchedCookbooks = await response.json();
            return fetchedCookbooks;
        } else {
            throw new Error(`Failed to fetch cookbooks. HTTP status: ${response.status}`);
        }
    } catch (error) {
        throw new Error(`Error fetching cookbooks: ${error.message}`);
    }
}

// Function to display cookbooks
async function displayCookbooks() {
    const userDataJSON = sessionStorage.getItem("user");
    const token = sessionStorage.getItem("token");
    const cookbookList = document.getElementById("cookbookList");
    cookbookList.innerHTML = ''; // Clear existing list

    if (userDataJSON) {
        const userData = JSON.parse(userDataJSON);
        const userId = userData.userId;

        let cookbooks = JSON.parse(sessionStorage.getItem('cookbooks')) || [];

        if (cookbooks.length === 0) {
            try {
                cookbooks = await fetchCookbooks(userId, token);
                // Update sessionStorage with fetched data
                sessionStorage.setItem('cookbooks', JSON.stringify(cookbooks));
            } catch (error) {
                console.error(error.message);
            }
        }

        if (cookbooks.length === 0) {
            cookbookList.innerHTML = '<li>No cookbooks found.</li>';
        } else {
            cookbooks.forEach(cookbook => {
                const listItem = document.createElement("li");
                const cookbookLink = document.createElement("a");
                cookbookLink.textContent = cookbook.name;
                cookbookLink.href = "#"; // Set a placeholder href
                cookbookLink.dataset.cookbookId = cookbook.id; // Store the cookbook ID as a data attribute
                listItem.appendChild(cookbookLink);
                cookbookList.appendChild(listItem);
                cookbookLink.addEventListener("click", function (event) {
                        event.preventDefault(); // Prevent the default link behavior
                        const cookbookId = this.dataset.cookbookId; // Retrieve the cookbook ID
                        retrieveRecipesByCookbookId(cookbookId); // Call a function to retrieve recipes
                });
            });
        }
    }
}

// Function to add a cookbook
async function addCookBook() {
    const name = document.getElementById("cookbookName").value;
    const type = document.getElementById("cookbookType").value;
    const userDataJSON = sessionStorage.getItem("user");
    const token = sessionStorage.getItem("token");

    if (userDataJSON) {
        const userData = JSON.parse(userDataJSON);
        const userId = userData.userId;

        const data = {
            name: name,
            userId: userId,
            type: type
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
                fetchCookbooks(userId, token);
                window.location.href = "/v1/account";
            } else {
                throw new Error("Add CookBook failed.");
            }
        } catch (error) {
            console.error("Add CookBook failed:", error.message);
        }
    } else {
        console.log("User data not found in sessionStorage.");
    }
}

async function retrieveRecipesByCookbookId(cookbookId) {
    const token = sessionStorage.getItem("token");

    if (token) {
        try {
            const response = await fetch(`/api/v1/recipes?cookbookId=${cookbookId}`, {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            });

            if (response.ok) {
                const recipes = await response.json();
                // Display the retrieved recipes on the page
                displayRecipes(recipes);
            } else {
                throw new Error(`Failed to retrieve recipes. HTTP status: ${response.status}`);
            }
        } catch (error) {
            console.error(`Error retrieving recipes: ${error.message}`);
        }
    } else {
        console.log("Token not found in session storage.");
    }
}

function displayRecipes(recipes) {
    const recipesContainer = document.getElementById("recipesContainer"); // Get the container where you want to display recipes

    // Clear the previous content in the container
    recipesContainer.innerHTML = '';

    if (recipes.length === 0) {
        // If no recipes were retrieved, display a message
        const message = document.createElement("p");
        message.textContent = "No recipes found for this cookbook.";
        recipesContainer.appendChild(message);
    } else {
        // Loop through each recipe and create a display element for it
        recipes.forEach(recipe => {
            const recipeItem = document.createElement("div");
            recipeItem.classList.add("recipe-item"); // You can apply CSS classes or styles as needed

            // Create elements to display recipe details (e.g., name, ingredients, instructions)
            const recipeName = document.createElement("h3");
            recipeName.textContent = recipe.name;

            const recipeIngredients = document.createElement("ul");
            recipe.ingredients.forEach(ingredient => {
                const ingredientItem = document.createElement("li");
                ingredientItem.textContent = ingredient;
                recipeIngredients.appendChild(ingredientItem);
            });

            const recipeInstructions = document.createElement("p");
            recipeInstructions.textContent = recipe.instructions;

            // Append the elements to the recipe item
            recipeItem.appendChild(recipeName);
            recipeItem.appendChild(recipeIngredients);
            recipeItem.appendChild(recipeInstructions);

            // Append the recipe item to the recipes container
            recipesContainer.appendChild(recipeItem);
        });
    }
}

// Function to remove a cookbook
function removeCookBook() {
    // Implement the logic for removing a cookbook here
}

// Add event listeners when the DOM content is loaded
document.addEventListener("DOMContentLoaded", addEventListeners);

// Call the function to display cookbooks when the page loads
window.addEventListener("load", displayCookbooks);
