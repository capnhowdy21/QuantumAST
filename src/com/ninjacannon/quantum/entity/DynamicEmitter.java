
package com.ninjacannon.quantum.entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitterFactory;
import org.newdawn.slick.particles.Particle;

/**
 * @author Dan Cannon
 */
public class DynamicEmitter extends ConfigurableEmitter implements ConfigurableEmitterFactory
{
    private Rectangle boundry;

    //The method required by the ConfigurableEmitterFactory interface
    public ConfigurableEmitter createEmitter(String name) {
        return new DynamicEmitter();
    }

    public DynamicEmitter() {
        super("dynamicemitter");
    }

    public void updateParticle(Particle particle, int delta) {
        super.updateParticle(particle, delta);
    }
}
