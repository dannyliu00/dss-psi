(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    function SubmitController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, level) {
    	
        $scope.submit = function (os) {
        	
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
	            profile.nonCompliant = true;
	        } else {
	            profile.nonCompliant = false;
	        }
	    	
	    	var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};
	    	
	    	if(level === "dealer") {
	            dealerProfileResource.submit(os)
	                .then(function(returnedOs) {
	                	profile.nonCompliant = returnedOs.nonCompliant;
	                	profile.orderSegments = returnedOs.orderSegments;
	                    $modalInstance.close();
	                });
	    	} else if(level === "dsm") {
	    		dealerProfileResource.approveRequested(os)
	    			.then(function(returnedos) {
	    				$modalInstance.close();
    			});
	    	}
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    submitValues.SubmitController = SubmitController;
})();