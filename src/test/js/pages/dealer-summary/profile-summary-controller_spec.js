/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    describe('DealerProfileSummaryCtrl', function() {
        var scope, expectedScope;
        var ctrl;

        beforeEach(function() {
            scope = {};
            expectedScope = {};

            ctrl = new dealerProfileSummary.DealerProfileSummaryCtrl(scope);
        });

        describe('constructor', function() {
            it('assigns scope when initialized', function() {
                expect(ctrl.scope).toEqual(scope);
            });
        });
    });
})();