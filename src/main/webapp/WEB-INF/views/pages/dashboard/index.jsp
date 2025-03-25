<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<%
    ObjectMapper objectMapper = new ObjectMapper();
    String companyNamesJson = objectMapper.writeValueAsString(request.getAttribute("companyNames"));
    String totalPaidValuesJson = objectMapper.writeValueAsString(request.getAttribute("totalPaidValues"));
    String totalPaidValueJson = objectMapper.writeValueAsString(request.getAttribute("paidAndUnpaid"));
    String invoiceStatusMapJson = objectMapper.writeValueAsString(request.getAttribute("invoiceStatusMap"));
    String clientidJson = objectMapper.writeValueAsString(request.getAttribute("clientid"));
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
                            const chart = new ApexCharts(document.querySelector("#pieChart"), {
                                series: totalPayment,
                                chart: {
                                    height: 350,
                                    type: 'pie',
                                    toolbar: {
                                        show: true
                                    },
                                    events: {
                                        click: function (event, chartContext, config) {
                                            // Lorsque le graphique est cliqué, rediriger vers l'URL spécifiée
                                            window.location.href = 'http://localhost:8080/payments';
                                        }
                                    }
                                },
                                labels: ['Payed', 'Amount due']
                            });
                            chart.render();
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
                            const companyNames = JSON.parse('<%=companyNamesJson %>');
                            const totalPaidValues = JSON.parse('<%=totalPaidValuesJson %>');
                            const clientIds = JSON.parse('<%=clientidJson %>');

                            const clientMap = {};
                            companyNames.forEach((name, index) => {
                                clientMap[name] = clientIds[index];
                            });

                            console.log("Mapping des clients:", clientMap);
                            console.log("clientIds:", clientIds);

                            const chart = new ApexCharts(document.querySelector("#barChart"), {
                                series: [{
                                    name: "Total Paid",
                                    data: totalPaidValues
                                }],
                                chart: {
                                    type: 'bar',
                                    height: Math.min(50 * companyNames.length, 700),
                                    events: {
                                        dataPointSelection: function(event, chartContext, config) {
                                            const companyName = config.w.config.xaxis.categories[config.dataPointIndex]; // Récupère le nom de l'entreprise cliquée
                                            const clientId = clientMap[companyName]; // Trouve l'ID client associé

                                            console.log("Entreprise cliquée:", companyName);
                                            console.log("clientId pour l'entreprise:", clientId);

                                            if (clientId) {
                                                window.location.href = `http://localhost:8080/clients/payments/`+clientId;
                                            } else {
                                                console.error("Client ID non trouvé pour l'entreprise:", companyName);
                                            }
                                        }
                                    }
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
                                    categories: companyNames, // Affiche les noms des entreprises normalement
                                    labels: {
                                        style: {
                                            cursor: 'pointer', // Ajoute un style de curseur en forme de main
                                        }
                                    }
                                }
                            });

                            chart.render();
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
                                },
                                // Détecter le clic sur une barre
                                onClick: function (event, elements) {
                                    if (elements.length > 0) {
                                        // Rediriger vers l'URL spécifiée
                                        window.location.href = 'http://localhost:8080/invoices';
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
<hr>


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
