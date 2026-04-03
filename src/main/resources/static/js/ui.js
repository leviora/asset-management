export function showToast(message, isError = false) {
    const toast = document.getElementById("toast");
    if (!toast) return;

    toast.textContent = message;

    toast.classList.remove("bg-green-600", "bg-red-600");
    toast.classList.add(isError ? "bg-red-600" : "bg-green-600");

    toast.classList.remove("opacity-0");

    setTimeout(() => {
        toast.classList.add("opacity-0");
    }, 3000);
}

export function showLoader(container) {
    container.innerHTML = `
        <div class="text-center py-10 text-gray-400">
            Loading...
        </div>
    `;
}