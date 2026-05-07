// asset-pagination.js

import { dashboardState } from "./state.js";
import { refreshDashboard } from "./dashboard.js";

export function renderPagination(pageData) {

    const pageInfo = document.getElementById("page-info");

    const prevBtn = document.getElementById("prev-page");

    const nextBtn = document.getElementById("next-page");

    if (!pageInfo || !prevBtn || !nextBtn) return;

    const currentPage = pageData.number ?? 0;

    const totalPages = pageData.totalPages ?? 1;

    pageInfo.textContent =
        `Page ${currentPage + 1} of ${totalPages}`;

    prevBtn.disabled = currentPage === 0;

    nextBtn.disabled = currentPage >= totalPages - 1;

    prevBtn.onclick = async () => {

        if (currentPage > 0) {

            dashboardState.page = currentPage - 1;

            await refreshDashboard();
        }
    };

    nextBtn.onclick = async () => {

        if (currentPage < totalPages - 1) {

            dashboardState.page = currentPage + 1;

            await refreshDashboard();
        }
    };
}