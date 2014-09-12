(function () {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder, $routeParams, dealerResource, dealerProfileResource, orderSegmentResourceMapper, appRoleResource, lastTab, currentDealer, blockUI) {

        this.orderSegmentResourceMapper = orderSegmentResourceMapper;

        var self = this;

        this.initialOrderSegments = null;

        // Change control
        $scope.isDirty = function () {
            return !angular.equals(self.initialOrderSegments, $scope.orderSegments);
        };

        $scope.resetChanges = function () {
            self.initialOrderSegments = angular.copy($scope.orderSegments);
        };

        var getRole = function () {
            appRoleResource.get().then(function (role) {
                $scope.role = role;
            }).then(function () {
                if ($scope.role.rsm === true) {
                    $scope.authLevel = 'adminQty';
                } else if ($scope.role.dsm === true) {
                    $scope.authLevel = 'dsmQty';
                } else {
                    $scope.authLevel = 'actual';
                }
                getDealer();
            });
        };

        var getDealer = function () {
            var dealer = {};
            if ($scope.authLevel === 'actual') {
                dealer = {type: $routeParams.type};
                dealerResource.get(dealer).then(function (returnedDealer) {
                    $scope.dealer = returnedDealer;
                    getProfile();
                });
            } else {
                dealer = {type: $routeParams.type, dealerId: currentDealer.dealerId};
                dealerResource.getNonDealer(dealer).then(function (returnedDealer) {
                    $scope.dealer = returnedDealer;
                    getProfile();
                });
            }
        };

        var getProfile = function () {
            // Block the user interface
            blockUI.start();

            var profile = {};

            if ($scope.authLevel === 'actual') {
                profile = {profileId: $routeParams.profileId};
                dealerProfileResource.get(profile)
                    .then(function (returnedProfile) {
                        finishProfile(returnedProfile);
                    }).then(function () {
                        $scope.recommendedGrandTotal = $scope.getRecTotals();
                    });
            } else {
                profile = {profileId: $routeParams.profileId, dealerId: currentDealer.dealerId};
                dealerProfileResource.nonDealer(profile)
                    .then(function (returnedProfile) {
                        finishProfile(returnedProfile);
                    }).then(function () {
                        $scope.recommendedGrandTotal = $scope.getRecTotals();
                    });
            }
            // Unblock the user interface
            blockUI.stop();
        };

        getRole();

        function findDistinctOSes(data) {
            var keys = [];

            keys.push(data[0].osName);
            if (data.length > 1) {
                for (var i = 1; i < data.length; i++) {
                    var newName = data[i].osName;
                    if (keys.indexOf(newName) === -1) {
                        keys.push(newName);
                    }
                }
            }

            return keys;
        }
        
        $scope.isEditable = function () {
            var status = $scope.profile.status;
            if (lastTab.profilesTab === 'current') {
            	if ($scope.role.dis === true) {
            		return false;
            	} else if ($scope.authLevel === 'adminQty' && status === 'EXCEPTION REQUESTED') {
                    return true;
                } else if ($scope.authLevel === 'dsmQty' && (status === 'PENDING' || status === 'RETURNED TO DSM')) {
                    return true;
                } else if ($scope.authLevel === 'actual' && (status === 'NOT STARTED' || status === 'RETURNED TO DEALER' || status === 'IN PROGRESS')) {
                    return true;
                }
            }
            return false;
        };

        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(25)
            .withBootstrap();

        $scope.getRecTotals = function() {
            var totalRecQty = 0;
            for (var j = 0, k = $scope.profile.periods.length; j < k; j++) {
                var periodCode = $scope.profile.periods[j].code;
                var recQty = 0, recMin = 0, recMax = 0;
                for (var i = 0, h = $scope.orderSegments.length; i < h; i++) {
                    var orderSegment = $scope.orderSegments[i];
                    if (($scope.profile.type === "motorcycle") && (orderSegment.osName !== null)) {
                        recSegmentTotal(orderSegment.osName);
                    }
                    if (orderSegment.periodCode === periodCode) {
                        recQty = recQty + orderSegment.recommended;
                        recMin = recMin + orderSegment.recMinimum;
                        recMax = recMax + orderSegment.recMaximum;
                    }
                }
                totalRecQty = totalRecQty + recQty;
            }
            return totalRecQty;
        };

        function recSegmentTotal(sub) {
            var segment = getSegment(sub);
            var total = 0;
            for (var i = 0, j = $scope.orderSegments.length; i < j; i++) {
                var checkList = segment.subSegments.indexOf($scope.orderSegments[i].osName);
                if (checkList !== -1) {
                    total = total + parseInt($scope.orderSegments[i].recommended);
                }
                segment.recommended = total;
            }
        }

        function getSegment(subSegment) {
            for (var i = 0, j = $scope.segments.length; i < j; i++) {
                var listCheck = $scope.segments[i].subSegments.indexOf(subSegment);
                if (listCheck !== -1) {
                    return $scope.segments[i];
                }
            }
        }

        // Calculate the total actuals for a period
        $scope.getPeriodTotal = function (periodCode) {
            var actQty = 0;
            var level = $scope.authLevel;

            for (var i = 0, j = $scope.orderSegments.length; i < j; i++) {
                var orderSegment = $scope.orderSegments[i];
                if (orderSegment.periodCode === periodCode) {
                    // Check if numeric
                    if ((orderSegment[level] * 1).toString() === orderSegment[level].toString()) {

                        var actual = parseInt(orderSegment[level]);
                        if (actual >= 0) {
                            actQty = actQty + actual;
                        }
                    }
                }
            }
            return actQty;
        };

        // Calculate total for a super segment
        $scope.getSegmentActualTotal = function (segment) {
            var level = $scope.authLevel;
            var total = 0;
            var count = 0;

            for (var i = 0, j = $scope.orderSegments.length; i < j; i++) {

                var checkList = segment.subSegments.indexOf($scope.orderSegments[i].osName);
                if (checkList !== -1) {
                    var orderSegmentVal = $scope.orderSegments[i][level];

                    if ((orderSegmentVal * 1).toString() === orderSegmentVal.toString()) {

                        var actual = parseInt(orderSegmentVal);
                        total += actual;
                        if (actual > 0) {
                            count++;
                        }
                    }
                }
            }
            return {actualTotal: total, orderSegmentCount: count};
        };

        // Get GrandTotal for all super segments
        $scope.getSuperSegmentGrandTotal = function () {
            var total = 0;
            for (var i = 0, j = $scope.segments.length; i < j; i++) {
                total += $scope.getSegmentActualTotal($scope.segments[i]).actualTotal;
            }
            return total;
        };

        $scope.segName = function (sub) {
            var nameCheck = 0;
            var segmentName = "";
            for (var i = 0, j = $scope.segments.length; i < j; i++) {
                nameCheck = $scope.segments[i].subSegments;
                if (nameCheck.indexOf(sub) !== -1) {
                    segmentName = $scope.segments[i].name;
                    i = $scope.segments.length;
                }
            }
            return segmentName;
        };


        function finishProfile(returnedProfile) {

            $scope.profile = returnedProfile;
            $scope.segments = returnedProfile.segments;

            for (var i = 0, j = returnedProfile.orderSegments.length; i < j; i++) {
                var seg = returnedProfile.orderSegments[i];

                if ($scope.authLevel === 'adminQty') {
                    if ((seg.adminQty === null || seg.adminQty <= -1) && (seg.dsmQty > -1 && seg.dsmQty !== null)) {
                        seg.adminQty = seg.dsmQty;
                    } else if ((seg.adminQty === null || seg.adminQty <= -1) && (seg.dsmQty <= -1 || seg.dsmQty === null)) {
                        seg.adminQty = seg.actual;
                        seg.dsmQty = seg.actual;
                    }
                } else if ($scope.authLevel === 'dsmQty' && (seg.dsmQty <= -1 || seg.dsmQty === null)) {
                    seg.dsmQty = seg.actual;
                }
            }
            if($scope.authLevel !== 'adminQty') {
            	$scope.orderSegments = displayValue(returnedProfile.orderSegments);
            } else {
            	$scope.orderSegments = returnedProfile.orderSegments;
            }
            $scope.distinctOS = findDistinctOSes($scope.orderSegments);

            // Remember the original data.
            $scope.resetChanges();
        }
        

        function displayValue(orderSegments) {
        	 
        	var status = $scope.profile.status;
        	var dQty = $scope.profile.orderSegments[0].dsmQty;
        	var rQty = $scope.profile.orderSegments[0].adminQty;
        	
        	for(var i = 0, j = orderSegments.length; i < j; i++){
	        	if ($scope.authLevel === 'actual') { 
		        	if (status === 'APPROVED' || status === 'APPROVED AS NONCOMPLIANT') {
		        		$scope.profile.orderSegments[i].actual = orderSegments[i].finalQty;
		        	} else if (status === 'PENDING' && rQty !== null && rQty > 0) {
		        		$scope.profile.orderSegments[i].actual = orderSegments[i].adminQty;
		        	} else if (status === 'PENDING' && dQty !== null && dQty > 0) {
		        		$scope.profile.orderSegments[i].actual = orderSegments[i].dsmQty;
		        	} else if (status === 'NOT STARTED') {
		        		$scope.profile.orderSegments[i].actual = orderSegments[i].recommended;
		        	}
	        	} else if ($scope.authLevel === 'dsmQty') {
	        		if (status === 'APPROVED AS REQUESTED' || status === 'APPROVED AS NONCOMPLIANT' || status === 'APPROVED AS COMPLIANT' || status === 'APPROVED WITH CHANGES'){
	        			$scope.profile.orderSegments[i].dsmQty = orderSegments[i].finalQty;
	        		}
	        	}
        	}
        	return $scope.profile.orderSegments;
        }
    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();
