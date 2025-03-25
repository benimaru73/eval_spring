<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itu.eval_spring.dto.invoice.InvoiceByClient" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<section class="table-components">
    <div class="container-fluid">
        <div class="title-wrapper pt-30">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <div class="title">
                        <h2>Liste des paiements du client</h2>
                    </div>
                </div>
            </div>
        </div>

        <div class="tables-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card-style mb-30">
                        <div class="table-wrapper table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><h6>ID</h6></th>
                                    <th><h6>status</h6></th>
                                    <th><h6>Montant dû</h6></th>
                                    <th><h6>Nom de l'entreprise</h6></th>
                                    <th><h6>Date de création</h6></th>
                                    <th><h6>Date d'échéance</h6></th>
                                    <th><h6>Réduction</h6></th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<InvoiceByClient> invoices = (List<InvoiceByClient>) request.getAttribute("invoices");
                                    for (InvoiceByClient invoice : invoices) {
                                %>
                                <tr>
                                    <td><p><%= invoice.getId() %></p></td>
                                    <td><p><%= invoice.getStatus()%> </p></td>
                                    <td><p><%= invoice.getAmount_due() %> </p></td>
                                    <td><p><%= invoice.getCompanyName() %></p></td>
                                    <td><p><%= invoice.getInvoiceDate() %></p></td>
                                    <td><p><%= invoice.getDue() %></p></td>
                                    <td><p><%= invoice.getReduction()*100 %> %</p></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
