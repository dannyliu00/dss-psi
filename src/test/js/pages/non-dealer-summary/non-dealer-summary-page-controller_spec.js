(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    describe('NonDealerSummaryController', function () {
        var scope, routeParams, location, appRoleResource, dsmProfilesResource;
        var rsmProfilesResource, expectedId, expectedType, expectedStatus, lastTab;
        var expectedProfilesDeferred, expectedProfiles, expectedRoleDeferred, expectedRole;
        var ctrl;

        beforeEach(function() {
            angular.mock.module('sellIn.pages.nondealersummary');

            angular.mock.module(function($provide) {
                appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
                $provide.decorator('appRoleResource', [function () {
                    return appRoleResource;
                }]);

                dsmProfilesResource = jasmine.createSpyObj('dsmProfilesResource', ['queryCurrent, queryHistory']);
                $provide.decorator('dsmProfilesResource', [function () {
                    return dsmProfilesResource;
                }]);

                rsmProfilesResource = jasmine.createSpyObj('rsmProfilesResource', ['queryCurrent, queryHistory']);
                $provide.decorator('rsmProfilesResource', [function () {
                    return rsmProfilesResource;
                }]);

                location = jasmine.createSpyObj('$location', ['path']);
                $provide.decorator('$location', [function () {
                    return location;
                }]);
            });

            expectedId = 111;
            expectedType = '2';
            expectedStatus = 'current';
            routeParams = {id: expectedId, status: expectedStatus};
            lastTab = 'current';
            expectedProfiles = [{id: 'id1', name: 'profile 1 name'}, {id: 'id2', name: 'profile 2 name'}];
        });

        beforeEach(inject(function ($q, $rootScope, appRoleResource, dsmProfilesResource, rsmProfilesResource) {
            expectedRoleDeferred = $q.defer();
            appRoleResource.get.andReturn(expectedRoleDeferred.promise);

            expectedProfilesDeferred = $q.defer();
            dsmProfilesResource.queryCurrent.andReturn(expectedProfilesDeferred.promise);
            dsmProfilesResource.queryHistory.andReturn(expectedProfilesDeferred.promise);
            rsmProfilesResource.queryCurrent.andReturn(expectedProfilesDeferred.promise);
            rsmProfilesResource.queryHistory.andReturn(expectedProfilesDeferred.promise);
        }));

        describe('constructor', function() {
            it('sets an array of profiles on scope for DSM', inject(function($rootScope,
                                                                             $location,
                                                                             dsmProfilesResource,
                                                                             appRoleResource,
                                                                             rsmProfilesResource,
                                                                             profilePageUrl,
                                                                             dsmUrl,
                                                                             productTabs,
                                                                             lastTab) {
                expectedRole = {dsm: true, rsm: false, sessionDetail: {ATV: 'Y'}};
                expectedRoleDeferred.resolve(expectedRole);
                var expectedDsm = {dsmId: expectedId, type: expectedType};

                expectedProfilesDeferred.resolve(expectedProfiles);

                ctrl = new nonDealerSummary.NonDealerSummaryController($rootScope, routeParams, $location,
                    dsmProfilesResource, appRoleResource, rsmProfilesResource, profilePageUrl, dsmUrl,
                    productTabs, lastTab);

                $rootScope.$digest();

                expect(appRoleResource.get).toHaveBeenCalled();
                expect($rootScope.role).toBeDefined();

//                expect(dsmProfilesResource.queryCurrent).toHaveBeenCalledWith(expectedDsm);
//                expect($rootScope.profiles).toBeDefined();
//
//                expect($rootScope.productTabs).toBeDefined();
//                expect($rootScope.productTabs.length).toEqual(1);
            }));
        });

//        describe('constructor', function() {
//            it('sets an array of profiles on scope for RSM', inject(function($rootScope,
//                                                                             $location,
//                                                                             appRoleResource,
//                                                                             dsmProfilesResource,
//                                                                             rsmProfilesResource,
//                                                                             profilePageUrl,
//                                                                             dsmUrl,
//                                                                             productTabs,
//                                                                             lastTab) {
//                expectedRole = {dsm: false, rsm: true, sessionDetail: {ATV: 'Y'}};
//                expectedRoleDeferred.resolve(expectedRole);
//                var expectedRsm = {rsmId: expectedId, type: '2'};
//                expectedProfilesDeferred.resolve(expectedProfiles);
//
//                ctrl = new nonDealerSummary.NonDealerSummaryController($rootScope, routeParams, $location,
//                    dsmProfilesResource, appRoleResource, rsmProfilesResource,
//                    profilePageUrl, dsmUrl, productTabs, lastTab);
//
//                $rootScope.$digest();
//
//                expect(appRoleResource.get).toHaveBeenCalled();
//                expect($rootScope.role).toBeDefined();
//
//                expect(rsmProfilesResource.queryCurrent).toHaveBeenCalledWith(expectedRsm);
//                expect($rootScope.profiles).toBeDefined();
//
//                expect($rootScope.productTabs).toBeDefined();
//                expect($rootScope.productTabs.length).toEqual(1);
//            }));
//        });
    });
})();