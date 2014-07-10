(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
    
    function ReasonModalController($scope, $modalInstance, reasonCodeResource, appRoleResource, dealerProfileResource, data, profile) {
    	
    	var authorizationRoleId = 0;
    	
//    	var reasonCommentData = {};
    	
    	appRoleResource.get().then(function(returnedRole){
    		authorizationRoleId = {roleId: returnedRole.authorizationRoleId};
    		reasonCodeResource.query(authorizationRoleId)
    			.then(function(returnedReasonCodes) {
    				$scope.reasonCodes = returnedReasonCodes;
    			});
    		});

        $scope.saveChanges = function (id) {
            for(var i=0; i<data.length; i++) {
                var item = data[i];
                item.dealerComments = this.reasonComments;
                item.reasonCode = id;
            }
            
            if(angular.element('input').hasClass('noncompliant')) {
	            profile.nonCompliant = true;
	        } else {
	            profile.nonCompliant = false;
	        }
	    	
	    	var os = {nonCompliant: profile.nonCompliant,orderSegments: data};
	    	
            dealerProfileResource.submit(os)
            	.then(function(returnedos) {
            		$modalInstance.close();
            });
       };
    	
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');  
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();