(function() {
	var summaryDatatables = sellInNamespace('sellIn.directives.summarydatatables');

    function SummaryDatatablesController ($scope, $location, DTOptionsBuilder) {
    	
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(10)
	        .withBootstrap();

    }

    summaryDatatables.SummaryDatatablesController = SummaryDatatablesController;
}());