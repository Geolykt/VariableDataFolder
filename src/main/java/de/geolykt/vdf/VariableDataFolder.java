package de.geolykt.vdf;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.badlogic.gdx.files.FileHandle;

import de.geolykt.starloader.Starloader;
import de.geolykt.starloader.api.NullUtils;
import de.geolykt.starloader.api.resource.DataFolderProvider;
import de.geolykt.starloader.mod.Extension;
import de.geolykt.starloader.transformers.ASMTransformer;
import net.minestom.server.extras.selfmodification.MinestomRootClassLoader;

public class VariableDataFolder extends Extension {

    class VDFTransformer extends ASMTransformer {

        @Override
        public boolean accept(@NotNull ClassNode node) {
            switch (node.name) {
            // If you are modifying this list, don't forget to also modify the list at
            // #isValidTarget!
            case "com/example/Main":
            case "snoddasmannen/namegenerator/LanguagePack":
            case "snoddasmannen/namegenerator/NameGenerator":
            case "snoddasmannen/galimulator/GalFX":
            case "snoddasmannen/galimulator/Space":
            case "snoddasmannen/galimulator/MapData":
            case "snoddasmannen/galimulator/bh": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/cy": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/gf": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/AudioManager":
            case "snoddasmannen/galimulator/AudioManager$AudioSample":
            case "snoddasmannen/galimulator/planetview/d": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/cd": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/actors/EaterOfStars":
            case "snoddasmannen/galimulator/ui/ms": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/Empire":
            case "snoddasmannen/galimulator/ProceduralScenarioSource$3": // anonymous class - high churn expected
            case "snoddasmannen/galimulator/SpeciesDescription":
            case "snoddasmannen/galimulator/ct": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/eu": // obfuscated - high churn expected
                remapClass(node);
                return true;
            }
            return false;
        }

        private @NotNull String generateSimpleString(@NotNull String location) {
            return DataFolderProvider.getProvider().provideAsPath().toString() + "/" + location;
        }

        @Override
        public boolean isValidTraget(@NotNull String internalName) {
            switch (internalName) {
            case "com/example/Main":
            case "snoddasmannen/namegenerator/LanguagePack":
            case "snoddasmannen/namegenerator/NameGenerator":
            case "snoddasmannen/galimulator/GalFX":
            case "snoddasmannen/galimulator/Space":
            case "snoddasmannen/galimulator/MapData":
            case "snoddasmannen/galimulator/bh": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/cy": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/gf": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/AudioManager":
            case "snoddasmannen/galimulator/AudioManager$AudioSample":
            case "snoddasmannen/galimulator/planetview/d": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/cd": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/actors/EaterOfStars":
            case "snoddasmannen/galimulator/ui/ms": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/Empire":
            case "snoddasmannen/galimulator/ProceduralScenarioSource$3": // anonymous class - high churn expected
            case "snoddasmannen/galimulator/SpeciesDescription":
            case "snoddasmannen/galimulator/ct": // obfuscated - high churn expected
            case "snoddasmannen/galimulator/eu": // obfuscated - high churn expected
                return true;
            default:
                return false;
            }
        }

        private void remapClass(ClassNode node) {
            for (MethodNode method : node.methods) {
                for (AbstractInsnNode insn : method.instructions) {
                    if (insn instanceof LdcInsnNode ldc) {
                        if (ldc.cst instanceof @NotNull String s) {
                            ldc.cst = remapString(s);
                        }
                    }
                }
            }
        }

        private @NotNull String remapString(@NotNull String original) {
            switch (original) {
            /* ==== Main ==== */
            case "data/icons/icon_128.png":
                return generateSimpleString("icons/icon_128.png");
            case "data/icons/icon_64.png":
                return generateSimpleString("icons/icon_64.png");
            case "data/icons/icon_32.png":
                return generateSimpleString("icons/icon_32.png");
            case "data/icons/icon_16.png":
                return generateSimpleString("icons/icon_16.png");
            case "data/sprites/":
                return generateSimpleString("sprites/");
            case "data/atlases/":
                return generateSimpleString("atlases/");
            /* ==== Namegenerator ==== */
            case "data/languages/":
                return generateSimpleString("languages/");
            case "data/languages/langs.dat":
                return generateSimpleString("languages/langs.dat");
            case "data/governments.txt":
                return generateSimpleString("governments.txt");
            case "data/seriousadjectives.txt":
                return generateSimpleString("seriousadjectives.txt");
            case "data/adjectives2.txt":
                return generateSimpleString("adjectives2.txt");
            case "data/cutenames.txt":
                return generateSimpleString("cutenames.txt");
            case "data/questnames.txt":
                return generateSimpleString("questnames.txt");
            case "data/revoltnames.txt":
                return generateSimpleString("revoltnames.txt");
            case "data/vanitynames.txt":
                return generateSimpleString("vanitynames.txt");
            /* ==== GalFX ==== */
            case "data/atlases/game.atlas":
                return generateSimpleString("atlases/game.atlas");
            case "data/fonts/Signika2.fnt":
                return generateSimpleString("fonts/Signika2.fnt");
            case "data/Audiowide-18.fnt":
                return generateSimpleString("Audiowide-18.fnt");
            case "data/Audiowide-24.fnt":
                return generateSimpleString("Audiowide-24.fnt");
            case "data/fonts/Raleway-SemiBold-new-24.fnt":
                return generateSimpleString("fonts/Raleway-SemiBold-new-24.fnt");
            case "data/fonts/Signika.fnt":
                return generateSimpleString("fonts/Signika.fnt");
            case "data/shaders/basic.vert":
                return generateSimpleString("shaders/basic.vert");
            case "data/shaders/ripple.frag":
                return generateSimpleString("shaders/ripple.frag");
            case "data/shaders/ship.frag":
                return generateSimpleString("shaders/ship.frag");
            case "data/shaders/planet.frag":
                return generateSimpleString("shaders/planet.frag");
            case "data/":
                return generateSimpleString("");
            case "data/sprites/flower.png":
                return generateSimpleString("sprites/flower.png");
            /* ==== Space ==== */
            case "data/vanityshipnames.txt":
                return generateSimpleString("vanityshipnames.txt");
            case "data/vanitydynasties.txt":
                return generateSimpleString("vanitydynasties.txt");
            case "data/vanityalliances.txt":
                return generateSimpleString("vanityalliances.txt");
            case "data/backstories.txt":
                return generateSimpleString("backstories.txt");
            case "data/custommottos.txt":
                return generateSimpleString("custommottos.txt");
            case "data/maps/quickmaps/":
                return generateSimpleString("maps/quickmaps/");
            case "data/mods/ships":
                return generateSimpleString("mods/ships");
            /* ==== MapData ==== */
            case "data/governments.json":
                return generateSimpleString("governments.json");
            /* ==== cy (OBFUSCATED) ==== */
            case "data/mottopreps.txt":
                return generateSimpleString("mottopreps.txt");
            case "data/mottonouns.txt":
                return generateSimpleString("mottonouns.txt");
            /* ==== gf (OBFUSCATED) ==== */
            case "data/i18n/Galimulator-strings":
                return generateSimpleString("i18n/Galimulator-strings");
            /* ==== AudioManager ==== */
            case "data/music":
                return generateSimpleString("music");
            /* ==== AudioManager$AudioSample ==== */
            case "data/sound/":
                return generateSimpleString("sound/");
            /* ==== planetview.d (OBFUSCATED) ==== */
            case "data/planetarynouns.txt":
                return generateSimpleString("planetarynouns.txt");
            case "data/emotions.txt":
                return generateSimpleString("emotions.txt");
            /* ==== cd (OBFUSCATED) ==== */
            case "data/version.txt":
                return generateSimpleString("version.txt");
            /* ==== EaterOfStars ==== */
            case "data/eaterblurbs.txt":
                return generateSimpleString("eaterblurbs.txt");
            /* ==== ui.ms (OBFUSCATED) ==== */
            case "data/maps/":
                return generateSimpleString("maps/");
            /* ==== Empire ==== */
            case "data/countryballs/":
                return generateSimpleString("countryballs/");
            /* ==== SpeciesDescription ==== */
            case "data/textfiles/appendages-endings.txt":
                return generateSimpleString("textfiles/appendages-endings.txt");
            /* ==== bh (OBFUSCATED) ==== */
            case "data/log.txt":
                return generateSimpleString("log.txt");
            /* ==== ct (OBFUSCATED) ==== */
            case "data/scenariolicense.txt":
                return generateSimpleString("scenariolicense.txt");
            default:
                return original;
            }
        }
    }

    @Override
    public void preInitialize() {
        MinestomRootClassLoader.getInstance().addTransformer(new VDFTransformer());
        if (DataFolderProvider.getProvider().provideAsFile().equals(new File("data"))) {
            File dataDir = NullUtils.requireNotNull(Starloader.getDataDir());
            if (!new File(dataDir, "version.txt").exists()) {
                JLabel label = new JLabel("The supplied data folder is likely invalid. Consider reviewing your Launcher settings.");
                Dimension location = Toolkit.getDefaultToolkit().getScreenSize();
                JPanel panel = new JPanel();
                panel.add(label);
                Dimension min = new Dimension(label.getPreferredSize());
                min.setSize(min.getWidth(), min.getHeight() * 5);
                panel.setPreferredSize(min);
                panel.setLayout(new GridBagLayout()); // center component within the popup
                location.setSize((location.getWidth() - label.getPreferredSize().getWidth()) / 2, (location.getHeight() - label.getPreferredSize().getHeight()) / 2);
                Popup p = PopupFactory.getSharedInstance().getPopup(null, panel, (int) location.getWidth(), (int) location.getHeight());
                p.show();
                try {
                    Thread.sleep(5000); // Make sure the user sees the popup
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                p.hide();
            }
            DataFolderProvider.setProvider(new DataFolderProvider.SimpleDataFolderProvider(dataDir,
                    new FileHandle(dataDir), NullUtils.requireNotNull(dataDir.toPath())));
        }
    }
}
