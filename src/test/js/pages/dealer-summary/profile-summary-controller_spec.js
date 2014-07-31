/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    describe('DealerProfileSummaryCtrl', function() {
        var scope, routeParams, location, dealerResource;
        var dealerProfilesResource, expectedDealerId, expectedProfileUrl, expectedSummaryUrl, expectedType;
        var expectedDealerDeferred, expectedProfilesDeferred, lastTab, expectedStatus, blockUI;
        var ctrl;

        beforeEach(function () {
            angular.mock.module('sellIn.pages.dealerProfileSummary');

            angular.mock.module(function ($provide) {
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function () {
                    return dealerResource;
                }]);
                dealerProfilesResource = jasmine.createSpyObj('dealerProfilesResource', ['queryCurrent', 'queryHistory']);
                $provide.decorator('dealerProfilesResource', [function () {
                    return dealerProfilesResource;
                }]);
                lastTab = jasmine.createSpyObj('lastTab', ['changeProductTab', 'changeProfilesTab']);
                $provide.decorator('lastTab', [function () {
                    return lastTab;
                }]);
                blockUI = jasmine.createSpyObj('blockUI', ['start', 'stop']);
                $provide.decorator('blockUI', [function() {
                    return blockUI;
                }]);
            });

            location = jasmine.createSpyObj('location', ['path']);

            expectedDealerId = 111;
            expectedR = 12345;
//	        expectedType = 'T';
            expectedStatus = 'current';
            routeParams = {dealerId: expectedDealerId, status: expectedStatus, r: expectedR};
            expectedProfileUrl = '/segment1/:dealerId/segment2/:profileId/segment3/:type';
            expectedSummaryUrl = '/segment1/:dealerId/segment2/:status';
        });

        beforeEach(inject(function ($q, $rootScope, dealerResource, dealerProfilesResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfilesDeferred = $q.defer();
            dealerProfilesResource.queryCurrent.andReturn(expectedProfilesDeferred.promise);
            dealerProfilesResource.queryHistory.andReturn(expectedProfilesDeferred.promise);

            scope = $rootScope.$new();
        }));

        describe('constructor', function () {
            it('initializes dealer on scope', inject(function ($rootScope, dealerResource, dealerProfilesResource, lastTab, blockUI) {
                lastTab.productTab = '';
                lastTab.profilesTab = '';

                ctrl = new dealerProfileSummary.DealerProfileSummaryCtrl(
                    scope,
                    routeParams,
                    location,
                    dealerResource,
                    dealerProfilesResource,
                    expectedProfileUrl,
                    expectedSummaryUrl,
                    lastTab,
                    blockUI);

	            var expectedDealerAndType = {dealerId: expectedDealerId, type: expectedType};
	            var expectedDealer = {dealerId: expectedDealerId, r: expectedR};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealerAndType);
                expect(lastTab.changeProfilesTab).toHaveBeenCalled();
                expect(dealerProfilesResource.queryCurrent).toHaveBeenCalled();
                expect(blockUI.start).toHaveBeenCalled();
                expect(blockUI.stop).toHaveBeenCalled();

            }));
        });

    });
})();
