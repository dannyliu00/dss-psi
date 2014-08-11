(function () {
    var summaryDatatables = sellInNamespace('sellIn.directives.summarydatatables');

    function SummaryDatatablesController($scope, DTOptionsBuilder) {
        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(25)
            .withBootstrap();

    }

    summaryDatatables.SummaryDatatablesController = SummaryDatatablesController;
}());