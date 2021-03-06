(function () {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    function NonDealerSummaryController($scope, $routeParams, $location, dsmProfilesResource, appRoleResource, rsmProfilesResource, profilePageUrl, dsmUrl, productTabs, lastTab, blockUI, currentDealer) {

        var setActiveProductTab = function (activeContent) {
            if (activeContent != null) {
                // if activeContent is passed in then that is what we want active
                $scope.activeTabFilter = activeContent;
            } else if (lastTab.productTab !== '') {
                // if lastTab.tab value is not empty make it active because we are returning to it
                $scope.activeTabFilter = lastTab.productTab;
            } else {
                // First time viewing this page so make the first tab the active tab
                $scope.activeTabFilter = $scope.productTabs[0].content;
                lastTab.changeProductTab($scope.activeTabFilter);
            }
        };

        var setProductTabStates = function () {
            for (var k = 0, l = $scope.productTabs.length; k < l; k++) {
                if ($scope.productTabs[k].content === $scope.activeTabFilter) {
                    $scope.productTabs[k].isActive = true;
                } else {
                    $scope.productTabs[k].isActive = false;
                }
            }
        };

        var loadDsmProfiles = function (role) {
            if (lastTab.profilesTab === 'current') {
                dsmProfilesResource.queryCurrent(role).then(function (returnedProfiles) {
                    $scope.profiles = returnedProfiles;
                });
            } else {
                dsmProfilesResource.queryHistory(role).then(function (returnedProfiles) {
                    $scope.profiles = returnedProfiles;
                });
            }
        };

        var loadRsmProfiles = function (role) {
            if (lastTab.profilesTab === 'current') {
                rsmProfilesResource.queryCurrent(role).then(function (returnedProfiles) {
                    $scope.profiles = returnedProfiles;
                });
            } else {
                rsmProfilesResource.queryHistory(role).then(function (returnedProfiles) {
                    $scope.profiles = returnedProfiles;
                });
            }
        };

        var loadProfiles = function () {
            // Block the user interface
            blockUI.start();

            lastTab.changeProfilesTab($routeParams.status);

            var user = {};

            var rVal = new Date().getTime();

            if ($scope.role.dsm === true) {
                user = {type: $scope.activeTabFilter, r: rVal};
                loadDsmProfiles(user);
            } else {
                user = {type: $scope.activeTabFilter, r: rVal};
                loadRsmProfiles(user);
            }

            // Unblock the user interface
            blockUI.stop();
        };

        $scope.tabContent = function (activeContent) {
            // set active tab
            setActiveProductTab(activeContent);
            // set product tabs as active/inactive based on user choice above
            setProductTabStates();
            // load profiles
            loadProfiles();
        };

        appRoleResource.get().then(function (role) {
            var currentTabs = [];
            // loop that builds the available product line tabs for the provided user role
            for (var i = 0, j = productTabs.length; i < j; i++) {
                if (productTabs[i].content === '2' && role.sessionDetail.ATV === 'Y') {
                    currentTabs.push(productTabs[i]);
                } else if (productTabs[i].content === '5' && role.sessionDetail.VIC === 'Y') {
                    currentTabs.push(productTabs[i]);
                } else if (productTabs[i].content === '6' && role.sessionDetail.RGR === 'Y') {
                    currentTabs.push(productTabs[i]);
                } else if (productTabs[i].content === 'Z' && role.sessionDetail.RZR === 'Y') {
                    currentTabs.push(productTabs[i]);
                } else if (productTabs[i].content === 'F' && role.sessionDetail.IND === 'Y') {
                    currentTabs.push(productTabs[i]);
                } else if (productTabs[i].content === 'E' && role.sessionDetail.SLG === 'Y') {
                	currentTabs.push(productTabs[i]);
                } else if (productTabs[i].content === '7' && role.sessionDetail.COM === 'Y') {
                	currentTabs.push(productTabs[i]);
                }
                $scope.productTabs = currentTabs;
            }
            $scope.role = role;
            $scope.profiles = $scope.tabContent();
        });

        $scope.navigateToNonDealerProfile = function (dealerId, profileId, type) {
            currentDealer.changeDealerId(dealerId);
            lastTab.changeProductTab('');
            var finalUrl = profilePageUrl
                .replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(finalUrl);
        };

        $scope.$on('tabClick', function (event, data) {

            lastTab.changeProfilesTab(data);
            var finalUrl = dsmUrl
                .replace(':type', lastTab.productTab)
                .replace(':status', data);
            $location.path(finalUrl);
        });

        $scope.$on('productTabClick', function (event, data) {

            lastTab.changeProductTab(data);
            var finalUrl = dsmUrl
                .replace(':type', data)
                .replace(':status', lastTab.profilesTab);
            $location.path(finalUrl);
        });
    }

    nonDealerSummary.NonDealerSummaryController = NonDealerSummaryController;
})();
	
		
