<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.itu.eval_spring.dto.payment.Payment" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<!-- ========== tab components start ========== -->
<section class="tab-components">
    <div class="container-fluid">
        <!-- ========== title-wrapper start ========== -->
        <div class="title-wrapper pt-30">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <div class="title">
                        <h2>"Modifier un paiement"</h2>
                    </div>
                </div>
                <!-- end col -->
                <div class="col-md-6">
                    <div class="breadcrumb-wrapper">
                        <a href="/payments">Retour à la liste</a>
                    </div>
                </div>
                <!-- end col -->
            </div>
            <!-- end row -->
        </div>
        <!-- ========== title-wrapper end ========== -->

        <!-- ========== form-elements-wrapper start ========== -->
        <div class="form-elements-wrapper">
            <div class="row">
                <div class="col-lg-6">
                    <!-- input style start -->
                    <div class="card-style mb-30">
                        <form action="<%
              Payment payment = (Payment) request.getAttribute("payment");
              out.print("/payments/edit" );
            %>" method="post">

                            <div class="input-style-1">
                                <label for="amount">Montant :</label>
                                <input type="number" id="amount" name="amount" value="<%= request.getAttribute("payment") != null ? ((Payment) request.getAttribute("payment")).getAmount() : "" %>" >
                            </div>
                            <input type="hidden" id="id" name="id" value="<%= ((Payment) request.getAttribute("payment")).getId() %>" >




                            <button type="submit" class="main-btn primary-btn square-btn btn-hover">Enregistrer</button>
                        </form>


                    </div>
                    <!-- end card -->
                </div>
            </div>
        </div>
        <!-- ========== form-elements-wrapper end ========== -->
    </div>
</section>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
