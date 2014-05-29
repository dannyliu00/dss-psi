(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    describe('DealerProfileController', function() {
        var scope, DTOptionsBuilder, DTOptions;
        var ctrl;

        beforeEach(function() {
            DTOptionsBuilder = jasmine.createSpyObj('DTOptionsBuilder', ['newOptions']);

            DTOptions = jasmine.createSpyObj('DTOptions', ['withPaginationType', 'withDisplayLength', 'withBootstrap']);
            DTOptionsBuilder.newOptions.andReturn(DTOptions);
            DTOptions.withPaginationType.andReturn(DTOptions);
            DTOptions.withDisplayLength.andReturn(DTOptions);
            scope = {};

            ctrl = new dealerProfiles.DealerProfileDirectiveController(scope, DTOptionsBuilder);
        });

        it('defines a DTOptions scope elements', function() {
            expect(DTOptionsBuilder.newOptions).toHaveBeenCalled();
            expect(DTOptions.withPaginationType).toHaveBeenCalled();
            expect(DTOptions.withDisplayLength).toHaveBeenCalled();
            expect(DTOptions.withBootstrap).toHaveBeenCalled();
        });
    });
})();
