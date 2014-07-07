(function() {
	var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    function NonDealerSummariesController ($scope, DTOptionsBuilder) {
    	 	
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(25)
	        .withBootstrap();
        
        $scope.lightColor = function(status) {
        	if(status === true){
        		return 'green-light';
        	}else{
        		return 'red-light';
        	}
        };
	       
    }

    nonDealerSummaries.NonDealerSummariesController = NonDealerSummariesController;
})();