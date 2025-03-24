<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<%
    ObjectMapper objectMapper = new ObjectMapper();
    String companyNamesJson = objectMapper.writeValueAsString(request.getAttribute("companyNames"));
    String totalPaidValuesJson = objectMapper.writeValueAsString(request.getAttribute("totalPaidValues"));
    String totalPaidValueJson = objectMapper.writeValueAsString(request.getAttribute("paidAndUnpaid"));
    String invoiceStatusMapJson = objectMapper.writeValueAsString(request.getAttribute("invoiceStatusMap"));
%>

<section class="section">
    <div class="row">


        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Project paid and unpaid</h5>

                    <!-- Pie Chart -->
                    <div id="pieChart"></div>

                    <script>
                        const totalPayment = JSON.parse('<%= totalPaidValueJson %>');
                        document.addEventListener("DOMContentLoaded", () => {
                            new ApexCharts(document.querySelector("#pieChart"), {
                                series: totalPayment,
                                chart: {
                                    height: 350,
                                    type: 'pie',
                                    toolbar: {
                                        show: true
                                    }
                                },
                                labels: ['Payed', 'Amount due']
                            }).render();
                        });
                    </script>
                    <!-- End Pie Chart -->

                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Payments by clients</h5>

                    <!-- Bar Chart -->
                    <div id="barChartContainer">
                        <div id="barChart"></div>
                    </div>

                    <script>
                        document.addEventListener("DOMContentLoaded", () => {
                            const companyNames = JSON.parse('<%= companyNamesJson %>');
                            const totalPaidValues = JSON.parse('<%= totalPaidValuesJson %>');

                            new ApexCharts(document.querySelector("#barChart"), {
                                series: [{
                                    data: totalPaidValues
                                }],
                                chart: {
                                    type: 'bar',
                                    height: Math.min(50 * companyNames.length, 700), // Ajuste la hauteur selon le nombre d'éléments
                                },
                                plotOptions: {
                                    bar: {
                                        borderRadius: 4,
                                        horizontal: true,
                                    }
                                },
                                dataLabels: {
                                    enabled: false
                                },
                                xaxis: {
                                    categories: companyNames
                                }
                            }).render();
                        });
                    </script>

                </div>
            </div>
        </div>

        <style>
            #barChartContainer {
                 height: 350px;
                overflow-y: auto; /* Active le scroll si nécessaire */
            }
        </style>
    </div>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Nombre de factures par statut</h5>

                <!-- Bar Chart -->
                <canvas id="barChart2" style="max-height: 400px;"></canvas>
                <script>
                    document.addEventListener("DOMContentLoaded", () => {
                        const ctx = document.querySelector('#barChart2');

                        // Récupérer les données converties en JSON
                        const invoiceStatusMap = <%= invoiceStatusMapJson %>;

                        // Transformer la map en deux tableaux (labels & data)
                        const labels = Object.keys(invoiceStatusMap);
                        const data = Object.values(invoiceStatusMap);

                        // Générer des couleurs aléatoires
                        const backgroundColors = labels.map(() => `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 0.2)`);
                        const borderColors = labels.map(() => `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 1)`);

                        new Chart(ctx, {
                            type: 'bar',
                            data: {
                                labels: labels,
                                datasets: [{
                                    label: 'Nombre de factures',
                                    data: data,
                                    backgroundColor: backgroundColors,
                                    borderColor: borderColors,
                                    borderWidth: 1
                                }]
                            },
                            options: {
                                responsive: true,
                                scales: {
                                    y: {
                                        beginAtZero: true
                                    }
                                }
                            }
                        });
                    });
                </script>
                <!-- End Bar CHart -->
            </div>
        </div>
    </div>

</section>
<script src="vendor/apexcharts/apexcharts.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/chart.js/chart.umd.js"></script>
<script src="vendor/echarts/echarts.min.js"></script>
<script src="vendor/quill/quill.min.js"></script>
<script src="vendor/simple-datatables/simple-datatables.js"></script>
<script src="vendor/tinymce/tinymce.min.js"></script>
<script src="vendor/php-email-form/validate.js"></script>


<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
