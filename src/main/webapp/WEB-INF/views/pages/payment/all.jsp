<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itu.eval_spring.dto.payment.PaymentByInvoice" %>
<%@ page import="com.itu.eval_spring.dto.payment.Payment" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<%
    List<Payment> payments = (List<Payment>) request.getAttribute("payments");
%>
<section class="table-components">
    <div class="container-fluid">
        <div class="title-wrapper pt-30">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <div class="title">
                        <h2>List of Payments for invoice n°
                            <%= (payments != null && !payments.isEmpty()) ? payments.get(0).getInvoiceId() : "is actually null" %>
                        </h2>
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
                                    <th><h6>Payment date</h6></th>
                                    <th><h6>Details</h6></th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (Payment payment : payments) {
                                %>
                                <tr>
                                    <td><p><%= payment.getAmount() %> €</p></td>
                                    <td><p><%= payment.getPaymentDate() %></p></td>

                                    <td>
                                        <div style="display: flex; gap: 5px;">
                                            <form action="<%= request.getContextPath() %>/payments/edit/<%= payment.getId() %>" method="get">
                                                <button type="submit" class="main-btn primary-btn square-btn btn-hover btn btn-warning"
                                                        style="padding: 5px 10px; font-size: 12px; width: auto; height: 30px;">
                                                    Modifier
                                                </button>
                                            </form>

                                            <form action="<%= request.getContextPath() %>/payments/delete" method="post">
                                                <input type="hidden" name="id" value="<%= payment.getId() %>">
                                                <button type="submit" class="main-btn primary-btn square-btn btn-hover"
                                                        style="padding: 5px 10px; font-size: 12px; width: auto; height: 30px;"
                                                        onclick="return confirm('Voulez-vous vraiment supprimer ce paiement ?')">
                                                    Supprimer
                                                </button>
                                            </form>
                                        </div>
                                    </td>

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
