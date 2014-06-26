(function() {
	var dsmSummaryDatatables = sellInNamespace('sellIn.directives.dsmsummarydatatables');

    function DsmSummaryDatatablesController ($scope, DTOptionsBuilder) {
    	
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(10)
	        .withBootstrap();

    }

    dsmSummaryDatatables.DsmSummaryDatatablesController = DsmSummaryDatatablesController;
}());