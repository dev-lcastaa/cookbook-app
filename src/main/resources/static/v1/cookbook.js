const recipeList = document.getElementById("recipeList");
// Access the "message" property from the server
var data = dataFromServer.cookBookId;

// Function to fetch recipes from an API or server
async function fetchRecipes() {
    const token = sessionStorage.getItem("token");
    console.log(data);
    try {
        const response = await fetch(`/api/v1/recipe?cookBookId=${data}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const recipes = await response.json();
            return recipes;
        } else {
            throw new Error(`Failed to fetch recipes. HTTP status: ${response.status}`);
        }
    } catch (error) {
        throw new Error(`Error fetching recipes: ${error.message}`);
    }
}

// Function to fetch and populate the recipe list
async function populateRecipeList() {

    try {
        // Fetch recipes from an API or server (replace with your API endpoint)
        const recipes = await fetchRecipes();

        // Clear existing recipe list
        recipeList.innerHTML = "";

        // Populate the list with fetched recipes
        recipes.forEach(recipe => {
            const li = document.createElement("li");
            const recipeLink = document.createElement("a");
            recipeLink.style.fontSize = "20px"; // Adjust the font size as needed
            // Set the recipe name as the link text
            recipeLink.textContent = recipe.name;

            // Add an event listener to handle click on the recipe link
            recipeLink.addEventListener("click", () => {
                // Toggle the visibility of the recipe details
                const recipeDetails = document.getElementById(`recipe-${recipe.id}`);
                if (recipeDetails.style.display === "none" || !recipeDetails.style.display) {
                    recipeDetails.style.display = "block";
                } else {
                    recipeDetails.style.display = "none";
                }
            });

            // Append the link to the list item
            li.appendChild(recipeLink);

            // Create a div for the recipe details
            const recipeDetails = document.createElement("div");
            recipeDetails.id = `recipe-${recipe.id}`;
            recipeDetails.classList.add("scroll-container"); // Add the scroll-container class
            recipeDetails.style.display = "none"; // Initially hide the details

            // Add the recipe details content
            recipeDetails.innerHTML = `
                <p>Ingredients:</p>
                <ul>
                    ${recipe.ingredients.map(ingredient => `<li>${ingredient}</li>`).join('')}
                </ul>
                <p>Steps:</p>
                <ol>
                    ${recipe.steps.map(step => `<li>${step}</li>`).join('')}
                </ol>
            `;

            // Append the recipe details to the list item
            li.appendChild(recipeDetails);

            // Append the list item to the recipe list
            recipeList.appendChild(li);
        });
    } catch (error) {
        console.error("Error fetching and populating recipes:", error);
    }
}

// Call the populateRecipeList function to populate the list
populateRecipeList();
