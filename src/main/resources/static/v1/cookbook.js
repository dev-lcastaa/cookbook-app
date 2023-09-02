// Access the "message" property from the server
var data = dataFromServer.cookBookId;

async function getRecipes(cookBookId) {
    const token = sessionStorage.getItem("token");
    try {
        const response = await fetch(`/api/v1/recipe?cookBookId=${cookBookId}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const fetchedRecipes = await response.json();
            return fetchedRecipes;
        } else {
            throw new Error(`Failed to fetch recipes. HTTP status: ${response.status}`);
        }
    } catch (error) {
        throw new Error(`Error fetching recipes: ${error.message}`);
    }
}

async function displayRecipes(cookBookId) {
    try {
        const recipes = await getRecipes(cookBookId);

        const recipeList = document.getElementById("recipeList");
        recipeList.innerHTML = ""; // Clear existing recipes

        recipes.forEach(recipe => {
            const li = document.createElement("li");

            // Create the recipe HTML structure
            let recipeHTML = `<div class="recipe-item">`;
            recipeHTML += `<h3>${recipe.name}</h3>`;

            if (recipe.ingredients && Array.isArray(recipe.ingredients)) {
                recipeHTML += `<h4>Ingredients:</h4>`;
                recipeHTML += `<ul>`;
                recipeHTML += recipe.ingredients.map(ingredient => `<li>${ingredient}</li>`).join('');
                recipeHTML += `</ul>`;
            } else {
                recipeHTML += `<p>No ingredients listed</p>`;
            }

            if (recipe.steps && Array.isArray(recipe.steps)) {
                recipeHTML += `<h4>Steps:</h4>`;
                recipeHTML += `<ol>`;
                recipeHTML += recipe.steps.map(step => `<li>${step}</li>`).join('');
                recipeHTML += `</ol>`;
            } else {
                recipeHTML += `<p>No steps provided</p>`;
            }

            recipeHTML += `</div>`;

            li.innerHTML = recipeHTML;
            recipeList.appendChild(li);
        });
    } catch (error) {
        console.error("Error fetching and displaying recipes:", error);
    }
}

// Call the displayRecipes function with the cookBookId from the server
displayRecipes(dataFromServer.cookBookId);