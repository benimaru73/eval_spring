<%@ page import="com.itu.eval_spring.dto.reduction.Reduction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<!-- ========== tab components start ========== -->
<%   Reduction reduction = (Reduction) request.getAttribute("reduction");%>
<section class="tab-components">
    <div class="container-fluid">

        <!-- ========== title-wrapper start ========== -->
        <div class="title-wrapper pt-30">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <div class="title">
                        <h2><%= reduction.getTaux() == 0 ? "Créer reduction" : "Modifier reduction" %></h2>
                    </div>
                </div>            </div>
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

    if (reduction.getTaux() == 0) {
        out.print("/reductions/create");
    } else {
        out.print("/reductions/edit/"+reduction.getId() );
    }
%>" method="post">

                            <div class="input-style-1">
                                <label for="taux">taux :</label>
                                <input type="text" id="taux" name="taux" value="<%= request.getAttribute("reduction") != null ? ((Reduction) request.getAttribute("reduction")).getTaux() : "" %>" required>

                            </div>



                            <button type="submit" class="main-btn primary-btn square-btn btn-hover">Enregistrer</button>
                        </form>

                    </div>
                    <!-- end card -->
                    <!-- ======= input style end ======= -->

                </div>

            </div>
            <!-- end row -->
        </div>
        <!-- ========== form-elements-wrapper end ========== -->

    </div>
</section>





<jsp:include page="/WEB-INF/views/layout/footer.jsp" />