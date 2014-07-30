(function() {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    function DsmButtonController($scope, $location, $modal, dsmUrl, lastTab) {
    	var caption = '';
    	var changeCaption = 0;
    	var dsmButtonCaption = "";
    	
        $scope.dsmButtonCaptionFill = function() {
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
        		dsmButtonCaption = "Submit for Exception";
        	} else if (isChanged() || angular.element('input').hasClass('noncompliant')) {
				dsmButtonCaption = "Approve with Changes";	
        	} else {
        		dsmButtonCaption = "Approve as Requested";
        	}
        	return  dsmButtonCaption;
        };
        
        
        $scope.dsmToSummary = function(dealerId) {
            lastTab.changeProductTab($scope.profile.typeCode);

            if($scope.isDirty()) {
            	openSaveDialog();
            } else {
                var finalDsmUrl = dsmUrl.replace(':id', dealerId).replace(':type', $scope.profile.typeCode).replace(':status', lastTab.profilesTab);
            	$location.path(finalDsmUrl);
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
    		changeCaption = 0;
        	if(dsmButtonCaption === "Approve as Requested") {
        		openSubmitDialog();
        	}else{	
                openReasonDialog();
        	}
        };
        
        $scope.sendBack = function() {
        	changeCaption = 1;
        	openReasonDialog();
        };
        
        function openReasonDialog() {
        	
        	var modalInstance = $modal.open({
				templateUrl: 'js/directives/modal/reason-modal-template.html',
				controller: 'reasonModalController',
				size: 'sm',
                resolve: {
		            orderSegments: function () {
		                return $scope.orderSegments;
		            },
		    		profile: function() {
		    			return $scope.profile;
		    		},
		    		caption: function() {
		    			if(changeCaption === 1) {
		    				caption = 'sendBack';
		    			} else {
		    				caption = dsmButtonCaption;
		    			}
		    			return caption;
		    		},
                    confirm: function() {
                        return '';
                    }
                }
			});

        	modalInstance.result.then(function () {
        		var finalDsmUrl = dsmUrl.replace(':id', $scope.role.dealerId)
                $location.path(finalDsmUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
		}
        
        function openSubmitDialog() {
        	
        	var dsm = "dsm";
        	
            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/submit-modal-template.html',
                controller: 'submitController',
                size: 'sm',
                resolve: {
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
            		profile: function() {
            			return $scope.profile;
            		},
            		level: function() {
            			return dsm;
            		},
                    confirm: function() {
                        return '';
                    }

                }
            });

            modalInstance.result.then(function () {
            	var finalDsmUrl = dsmUrl.replace(':id', $scope.role.dealerId)
                $location.path(finalDsmUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }
        
        function isChanged() {
        	for(var i = 0; i < $scope.orderSegments.length; i++) {
        		if($scope.orderSegments[i].actual !== parseInt($scope.orderSegments[i].dsmQty)) {
        			return true;
        		}
        	}
    		return false;
        } 
    }

    dsmButton.DsmButtonController = DsmButtonController;
})();