const addCookbookButton = document.querySelector("#addCookbookButtonForm");
const modal = document.querySelector("#addCookbookModal");
const closeButton = document.querySelector(".close-button");
const cookbookForm = document.querySelector("#cookbookForm");

// Show the modal when the "Add Cook Book" button is clicked
addCookbookButton.addEventListener("click", () => {
    modal.style.display = "block";
});

// Hide the modal when the close button is clicked
closeButton.addEventListener("click", () => {
    modal.style.display = "none";
});

// Hide the modal when the user clicks outside of it
window.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

// Handle form submission (you can add your own logic)
cookbookForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const cookbookName = cookbookForm.elements.cookbookName.value;
    // Save cookbookName to sessionStorage or perform your desired action
    // Close the modal
    modal.style.display = "none";
});