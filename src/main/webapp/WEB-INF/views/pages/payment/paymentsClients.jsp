<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itu.eval_spring.dto.ClientDTO" %>
<%@ page import="com.itu.eval_spring.dto.PaymentDTO" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<section class="table-components">
    <div class="container-fluid">
        <div class="title-wrapper pt-30">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <div class="title">
                        <h2>Paiements de <%= ((ClientDTO) request.getAttribute("client")).getCompanyName() %></h2>
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
                                    <th><h6>Montant (€)</h6></th>
                                    <th><h6>Source</h6></th>
                                    <th><h6>Date</h6></th>
                                    <th><h6>Facture</h6></th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<PaymentDTO> payments = (List<PaymentDTO>) request.getAttribute("payments");
                                    for (PaymentDTO payment : payments) {
                                %>
                                <tr>
                                    <td><p><%= payment.getId() %></p></td>
                                    <td><p><%= payment.getAmount() %> €</p></td>
                                    <td><p><%= payment.getPaymentSource() %></p></td>
                                    <td><p><%= payment.getPaymentDate() %></p></td>
                                    <td><p>Facture #<%= payment.getInvoiceId() %></p></td>
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
