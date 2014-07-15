(function() {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    function DsmButtonController($scope, $location, $modal, dealerSummaryPageUrl, profilePageUrl, dsmUrl) {
    	
        $scope.dsmButtonCaptionFill = function() {
        	var dsmButtonCaption = "";
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
        		dsmButtonCaption = "Submit for Exception";
        	} else if ($scope.dirtyIndicator > 1 && isChanged()) {
				dsmButtonCaption = "Approve with Changes";	
        	} else {
        		dsmButtonCaption = "Approve as Requested";
        	}
        	return  dsmButtonCaption;
        };
        
        
        $scope.dsmToSummary = function(dealerId) {
            var dsmChanges = $scope.dirtyIndicator;

            switch(dsmChanges) {
                case 0:
                case 1:
                	var finalDsmUrl = dsmUrl.replace(':id', dealerId);
                    $location.path(finalDsmUrl);
                    break;
                default:
                    openSaveDialog();
                    break;
            }
        };

        function openSaveDialog () {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/unsaved-changes-modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm',
                resolve: {
                    role: function () {
                        return $scope.role;
                    },
		            orderSegments: function () {
		                return $scope.orderSegments;
		            },
		    		profile: function() {
		    			return $scope.profile;
		    		}
                }
            });

            modalInstance.result.then(function () {
            	var finalDsmUrl = dsmUrl.replace(':id', $scope.role.dealerId);
                $location.path(finalDsmUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        $scope.approveSubmit = function() {
            if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
                openReasonDialog();
            }
        };
        
        $scope.sendBack = function() {
        	openReasonDialog();
        };
        
        function openReasonDialog() {
        	
        	var modalInstance = $modal.open({
				templateUrl: 'js/directives/modal/reason-modal-template.html',
				controller: 'reasonModalController',
				size: 'sm'
			});

        	modalInstance.result.then(function (reasonCommentData) {
                $location.path(dsmUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
		}
        
        function isChanged() {
        	for(var i = 0; i < $scope.orderSegments.length; i++) {
        		if($scope.orderSegments[i].actual !== $scope.orderSegments[i].dsmQty) {
        			return true;
        		}
        	}
        	return false;
        } 
    }

    dsmButton.DsmButtonController = DsmButtonController;
})();