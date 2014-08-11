/**
 * Created by bericks on 5/13/2014.
 */
(function () {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    describe('DealerProfileSummaryCtrl', function () {
        var scope, routeParams, location;
        var dealerProfilesResource, expectedProfileUrl, expectedSummaryUrl;
        var expectedProfilesDeferred, lastTab, expectedStatus, blockUI;

        beforeEach(function () {
            angular.mock.module('sellIn.pages.dealerProfileSummary');

            angular.mock.module(function ($provide) {

                dealerProfilesResource = jasmine.createSpyObj('dealerProfilesResource', ['queryCurrent', 'queryHistory']);
                $provide.decorator('dealerProfilesResource', [function () {
                    return dealerProfilesResource;
                }]);
                lastTab = jasmine.createSpyObj('lastTab', ['changeProductTab', 'changeProfilesTab']);
                $provide.decorator('lastTab', [function () {
                    return lastTab;
                }]);
                blockUI = jasmine.createSpyObj('blockUI', ['start', 'stop']);
                $provide.decorator('blockUI', [function () {
                    return blockUI;
                }]);
            });

            location = jasmine.createSpyObj('location', ['path']);
            expectedR = 12345;
            expectedStatus = 'current';
            routeParams = {status: expectedStatus, r: expectedR};
            expectedProfileUrl = '/segment1/:profileId/segment2/:type';
            expectedSummaryUrl = '/segment1/:status';
        });

        beforeEach(inject(function ($q, $rootScope, dealerProfilesResource) {

            expectedProfilesDeferred = $q.defer();
            dealerProfilesResource.queryCurrent.andReturn(expectedProfilesDeferred.promise);
            dealerProfilesResource.queryHistory.andReturn(expectedProfilesDeferred.promise);

            scope = $rootScope.$new();
        }));

        describe('constructor', function () {
            it('initializes dealer on scope', inject(function ($rootScope, dealerProfilesResource, lastTab, blockUI) {
                lastTab.productTab = '';
                lastTab.profilesTab = '';

                ctrl = new dealerProfileSummary.DealerProfileSummaryCtrl(
                    scope,
                    routeParams,
                    location,
                    dealerProfilesResource,
                    expectedProfileUrl,
                    expectedSummaryUrl,
                    lastTab, blockUI);

                expect(lastTab.changeProfilesTab).toHaveBeenCalled();
                expect(dealerProfilesResource.queryCurrent).toHaveBeenCalled();
                expect(blockUI.start).toHaveBeenCalled();
                expect(blockUI.stop).toHaveBeenCalled();

            }));
        });

    });
})();
