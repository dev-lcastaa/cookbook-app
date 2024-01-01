// Function to make the API call
async function fetchRecipe() {

    //get token from sessionStorage
    const token = sessionStorage.getItem("token");

    //show loading spinner
    showLoadingSpinner();

    //scans html for the following IDs
    const ingredients = document.getElementById('ingredientsForFinder').value;
    const ethnicity = document.getElementById('ethnicity').value;


    try {
        //recipe API endpoint
        const apiUrl = '/v1/api/finder/recipe';

        //response from the fetch
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ethnicity: ethnicity,
                ingredients: ingredients
            }),
        });

        //if the response is a 200 ok
        if (response.ok) {
            const data = await response.json();

            // Create an empty object to store the parsed data
            const recipeObject = validateResponse(data);

            // Now, recipeObject contains the parsed data
            console.log('Parsed Recipe Object:', recipeObject);

            // You can access individual properties like recipeObject.name, recipeObject.ingredients, etc.
            document.getElementById('recipeName').textContent = recipeObject.name;
            document.getElementById('recipeIngredients').textContent = recipeObject.ingredients;
            document.getElementById('recipeSteps').textContent = recipeObject.steps;

            populateCookbookSelect();
        }

        // if the response is not a 200 ok
        // inside the modal it will display API call failed
        else { document.getElementById('recipeContent').innerHTML = 'API call failed.';}
    }

    // catches any error and logs it to the console and
    // inside the modal it will display API call failed
    catch (error) {
        console.error('API call error:', error);
        document.getElementById('recipeContent').innerHTML = 'API call failed.';
    }

    //last it will hide the loading spinner
    finally { hideLoadingSpinner(); }
}

// Function to validate response from OpenAi is of specified format
function validateResponse(response) {
    // If the response is already an object and has the required properties, return it as-is
    if (typeof response === 'object' && response.name && response.ingredients && response.steps) {
        return response;
    }

    // Try to parse the response as JSON
    try {
        const recipe = JSON.parse(response);

        // Check if the parsed response has the required properties
        if (recipe && recipe.name && recipe.ingredients && recipe.steps) {
            return recipe;
        } else {
            // If the parsed response is missing required properties, construct the object using the response's key-value pairs
            const recipeObject = {
                name: recipe.name || 'Recipe Name Not Available',
                ingredients: recipe.ingredients || 'Ingredients Not Available',
                steps: recipe.steps || 'Recipe Steps Not Available'
            };
            return recipeObject;
        }
    } catch (error) {
        // Create an empty object to store the parsed data
        const recipeObject = {};

        // Handle any errors in the response gracefully
        recipeObject.name = 'Recipe Name Not Available';
        recipeObject.ingredients = 'Ingredients Not Available';
        recipeObject.steps = 'Recipe Steps Not Available';

        return recipeObject;
    }
}

// Function to show the loading spinner and hide the modal content
function showLoadingSpinner() {
    document.getElementById('loadingSpinner').style.display = 'block';
    document.getElementById('recipeContent').style.display = 'none';
}

// Function to hide the loading spinner and display the modal content
function hideLoadingSpinner() {
    document.getElementById('loadingSpinner').style.display = 'none';
    document.getElementById('recipeContent').style.display = 'block';
}

// Function to dynamically populate the cookbook select element
function populateCookbookSelect() {
    // Retrieve the list of cookbooks from session storage
    const cookbooks = JSON.parse(sessionStorage.getItem('cookbooks')) || [];

    // Get the select element
    const cookbookSelect = document.getElementById('cookbookList');

    // Clear existing options in the select element
    cookbookSelect.innerHTML = '';

    // Add a default option
    const defaultOption = document.createElement('option');
    defaultOption.value = ''; // Empty value
    defaultOption.text = 'Select Cookbook';
    cookbookSelect.appendChild(defaultOption);

    // Populate the select element with options from the cookbooks array
    cookbooks.forEach((cookbook) => {
        const option = document.createElement('option');
        option.value = cookbook.id; // Use the 'id' property as the value
        option.text = cookbook.name; // Assuming each cookbook object has a 'name' property
        cookbookSelect.appendChild(option);
    });
}


function addRecipeToCookBook(){
    // Get the selected cookbook index from the select element
    const selectedCookbookIndex = document.getElementById('cookbookList').value;
    console.log(selectedCookbookIndex)

    // Retrieve the list of cookbooks from session storage
    const cookbooks = JSON.parse(sessionStorage.getItem('cookbooks')) || [];

    // Get the recipe details from the response or wherever it's stored
    const newRecipeData = {
        name: document.getElementById('recipeName').textContent,
        cookBookId: selectedCookbookIndex,
        ingredients: document.getElementById('recipeIngredients').textContent,
        steps: document.getElementById('recipeSteps').textContent,
    };

    const token = sessionStorage.getItem("token");
    fetch("/v1/api/recipe", {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newRecipeData)
        }).then(function (response) {
                  if (response.ok) {
                      // Parse the response body to get the token
                      alert("Added Recipe to Cookbook")
                  } else {
                      alert("Failed to add Recipe")
                      throw new Error("Failed to add Recipe");
                  }
              })

}

// Attach a click event listener to the "Get Recipe" button
document.getElementById('fetchRecipeModalButton').addEventListener('click', fetchRecipe);

// Attached a click event listener to the add recipe to cookbook button
document.getElementById('addRecipeToCookBookButton').addEventListener('click', addRecipeToCookBook);