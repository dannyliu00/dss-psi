(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
    
    function ReasonModalController($scope, $modalInstance, reasonCodeResource, appRoleResource, dealerProfileResource, orderSegments, profile, caption, confirm) {
    	
    	var comments = "";
    	var authorizationRoleId = 0;
    	
    	appRoleResource.get().then(function(returnedRole){
    		$scope.role = returnedRole;
    		authorizationRoleId = {roleId: returnedRole.authorizationRoleId};
    		reasonCodeResource.query(authorizationRoleId)
    			.then(function(returnedReasonCodes) {
    				$scope.reasonCodes = returnedReasonCodes;
    			});
    		});

        $scope.saveChanges = function (id) {
        	
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
	            profile.nonCompliant = true;
	        } else {
	            profile.nonCompliant = false;
	        }
        	
        	if($scope.role.rsm === true) {
        		comments = 'adminComments';
        	} else if ($scope.role.dsm === true) {
        		comments = 'dsmComments';
        	} else {
        		comments = 'dealerComments';
        	}
        	
            for(var i=0; i<orderSegments.length; i++) {
                var item = orderSegments[i];
                item[comments] = this.reasonComments;
                item.reasonCode = id;
                if($scope.role.dealer === true) {
                    item.dealerEmail = confirm;
                }
            }
            
	    	var os = {nonCompliant: profile.nonCompliant,orderSegments: orderSegments};
	    	
	    	if(caption === 'dealerReason') {
	            dealerProfileResource.submit(os)
	            	.then(function(returnedos) {
	            		$modalInstance.close();
	            });
	    	} else if(caption === "Submit for Exception") {
	    		dealerProfileResource.submitException(os)
        			.then(function(returnedos) {
        				$modalInstance.close();
    			});
	    	} else if(caption === "Approve with Changes") {
	    		dealerProfileResource.approveWChanges(os)
    				.then(function(returnedos) {
    					$modalInstance.close();
    				});
	    	} else if(caption === "sendBack") {
	    		dealerProfileResource.sendBack(os)
	    			.then(function(returnedos) {
	    				$modalInstance.close();
	    			});
	    	} else if(caption ==="Return to DSM") {
	    		dealerProfileResource.returnDsm(os)
	    			.then(function(returnedos) {
	    				$modalInstance.close();
	    			});
	    	} else if(caption === "Approve as Compliant") {
	    		dealerProfileResource.compliant(os)
	    			.then(function(returnedos) {
	    				$modalInstance.close();
	    			});
	    	} else if(caption === "Approve as Non-Compliant") {
	    		dealerProfileResource.nonCompliant(os)
	    			.then(function(returnedos) {
	    				$modalInstance.close();
	    			});
	    	}
       };
    	
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');  
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();