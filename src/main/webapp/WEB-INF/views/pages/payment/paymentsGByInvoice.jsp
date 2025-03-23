<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itu.eval_spring.dto.Payment.PaymentByInvoice" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<section class="table-components">
    <div class="container-fluid">
        <div class="title-wrapper pt-30">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <div class="title">
                        <h2>List of Paiements by project</h2>
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
                                    <th><h6>Amount</h6></th>
                                    <th><h6>Status</h6></th>
                                    <th><h6>Date invoice</h6></th>
                                    <th><h6>Rest due</h6></th>
                                    <th><h6>Details</h6></th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<PaymentByInvoice> payments = (List<PaymentByInvoice>) request.getAttribute("paymentGByInvoice");
                                    for (PaymentByInvoice payment : payments) {
                                %>
                                <tr>
                                    <td><p><%= payment.getAmount() %> €</p></td>
                                    <td><p><%= payment.getStatus() %></p></td>
                                    <td><p><%= payment.getInvoiceDate() %></p></td>
                                    <td><p><%= payment.getNewAmountDue() %></p></td>
                                    <td><a href="<%= request.getContextPath() %>/payments/<%= payment.getInvoiceId() %>">view</a></td>

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
