(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    function SubmitController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, level, confirm) {
    	
        $scope.submit = function (os) {
        	
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
	            profile.nonCompliant = true;
	        } else {
	            profile.nonCompliant = false;
	        }
	    	
	    	var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};
	    	
	    	if(level === "dealer") {
	    		for(var i = 0; i < orderSegments.length; i++) {
	    			orderSegments[i].dealerEmail = confirm;
	    		}
	    		
	    		os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};
	    		
	            dealerProfileResource.submit(os)
	                .then(function(returnedos) {
	                    $modalInstance.close(returnedos.successful);
	                });
	    	} else if(level === "dsm") {
	    		dealerProfileResource.approveRequested(os)
	    			.then(function(returnedos) {
	    				$modalInstance.close(returnedos.successful);
    			});
	    	}
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    submitValues.SubmitController = SubmitController;
})();