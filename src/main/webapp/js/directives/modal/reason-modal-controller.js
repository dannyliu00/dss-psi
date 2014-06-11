(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
    
    function ReasonModalController($scope, $modalInstance,$routeParams,reasonCodeResource, appRoleResource) {
    	
    	var authorizationRoleId = {};
    	        appRoleResource.get(authorizationRoleId).then(function(returnedauthorizationRoleId){
    	        	$scope.roleId = returnedauthorizationRoleId;
    	        });
        var reasonCode = {roleId:$routeParams.roleId};
    	    reasonCodeResource.get(reasonCode).then(function(reasonCode) {
    	        $scope.reasonCodes = returnedreasonCode;
    	    });
    	    
    	$scope.selectedReasonCode = "";
    	
    	$scope.openReasonDialog = function() {
			$modal.open({
				templateUrl: 'js/directives/modal/reason-modal-template.html',
				controller: 'reason-modal-controller',
				size: 'sm'
			});
		};
    	    
        $scope.saveChanges = function () {
            $modalInstance.close();
       };
    	
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();