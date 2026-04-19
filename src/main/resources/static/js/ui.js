let toastTimeout;

export function showToast(message, type = "success") {
    const toast = document.getElementById("toast");
    if (!toast) return;

    toast.textContent = message;

    toast.classList.remove(
        "bg-orange-500/90",
        "bg-red-500/90",
        "bg-yellow-500/90"
    );

    if (type === "error") {
        toast.classList.add("bg-red-500/90");
    } else if (type === "warning") {
        toast.classList.add("bg-yellow-500/90");
    } else {
        toast.classList.add("bg-orange-500/90");
    }

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