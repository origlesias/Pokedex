package com.example.oriol.pokedex;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Oriol on 15/02/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pokedex.db";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Pokemon "+
                        "(id integer primary key, name text,type1 text,type2 text, description text)"
        );
        db.execSQL(
                "create table Media "+
                        "(image blob)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Pokemon");
        onCreate(db);
    }

    public void initPokedex(){

        String[] pokemons={"001, Bulbasaur, grass, poison, It can go for days without eating a single morsel. In the bulb on its back, it stores energy." ,
                "002, Ivysaur, grass, poison, The bulb on its back grows by drawing energy. It gives off an aroma when it is ready to bloom.",
                "003, Venusaur, grass, poison, The flower on its back catches the suns rays. The sunlight is then absorbed and used for energy.",
                "004, Charmander, fire, NULL, The flame at the tip of its tail makes a sound as it burns. You can only hear it in quiet places." ,
                "005, Charmeleon, fire, NULL, Tough fights could excite this POKEMON. When excited, it may blow out bluish-white flames.",
                "006, Charizard, fire, flying, When expelling a blast of super hot fire, the red flame at the tip of its tail burns more intensely." ,
                "007, Squirtle, water, NULL, Shoots water at prey while in the water. Withdraws into its shell when in danger." ,
                "008, Wartortle, water, NULL, When tapped, this POKEMON will pull in its head, but its tail will still stick out a little bit.",
                "009, Blastoise, water, NULL, Once it takes aim at its enemy, it blasts out water with even more force than a fire hose." ,
                "010, Caterpie, bug, NULL, If you touch the feeler on top of its head, it will release a horrible stink to protect itself.",
                "011, Metapod, bug, NULL, Hardens its shell to protect itself. However, a large impact may cause it to pop out of its shell.",
                "012, Butterfree, bug, flying, Its wings, covered with poisonous powders, repel water. This allows it to fly in the rain.",
                "013, Weedle, bug, poison, Beware of the sharp stinger on its head. It hides in grass and bushes where it eats leaves.",
                "014, Kakuna, bug, poison, Able to move only slightly. When endangered, it may stick out its stinger and poison its enemy.",
                "015, Beedrill, bug, poison, It has 3 poisonous stingers on its forelegs and its tail. They are used to jab its enemy repeatedly.",
                "016, Pidgey, normal, flying, Very docile. If attacked, it will often kick up sand to protect itself rather than fight back.",
                "017, Pidgeotto, normal, flying, This POKEMON is full of vitality. It constantly flies around its large territory in search of prey.",
                "018, Pidgeot, normal, flying, This POKEMON flies at Mach 2 speed, seeking prey. Its large talons are feared as wicked weapons.",
                "019, Rattata, normal, NULL, Will chew on anything with its fangs. If you see one, it is certain that 40 more live in the area.",
                "020, Raticate, normal, NULL, Its hind feet are webbed. They act as flippers, so it can swim in rivers and hunt for prey." ,
                "021, Spearow, normal, flying, Inept at flying high. However, it can fly around very fast to protect its territory.",
                "022, Fearow, normal, flying, A POKEMON that dates back many years. If it senses danger, it flies high and away, instantly." ,
                "023, Ekans, poison, NULL, The older it gets, the longer it grows. At night, it wraps its long body around tree branches to rest.",
                "024, Arbok, poison, NULL, The frightening patterns on its belly have been studied. Six variations have been confirmed.",
                "025, Pikachu, electric, NULL, It keeps its tail raised to monitor its surroundings. If you yank its tail, it will try to bite you." ,
                "026, Raichu, electric, NULL, When electricity builds up inside its body, it becomes feisty. It also glows in the dark.",
                "027, Sandshrew, ground, NULL, Its body is dry. When it gets cold at night, its hide is said to become coated with a fine dew.",
                "028, Sandslash, ground, NULL, It is skilled at slashing enemies with its claws. If broken, they start to grow back in a day.",
                "029, Nidoran F, poison, NULL, A mild-mannered POKEMON that does not like to fight. Beware, its small horns secrete venom.",
                "030, Nidorina, poison, NULL, When resting deep in its burrow, its thorns always retract. This is proof that it is relaxed.",
                "031, Nidoqueen, poison, ground, Tough scales cover the sturdy body of this POKeMON. It appears that the scales grow in cycles.",
                "032, Nidoran M, poison, NULL, Its large ears are always kept upright. If it senses danger, it will attack with a poisonous sting.",
                "033, Nidorino, poison, NULL, Its horns contain venom. If they are stabbed into an enemy, the impact makes the poison leak out.",
                "034, Nidoking, poison, ground, Its steel-like hide adds to its powerful tackle. Its horns are so hard, they can pierce a diamond.",
                "035, Clefairy, fairy, NULL, Adored for their cute looks and playfulness. They are thought to be rare, as they do not appear often." ,
                "036, Clefable, fairy, NULL, They appear to be very protective of their own world. It is a kind of fairy, rarely seen by people.",
                "037, Vulpix, fire, NULL, Both its fur and its tails are beautiful. As it grows, the tails split and form more tails.",
                "038, Ninetales, fire, NULL, According to an enduring legend, 9 noble saints were united and reincarnated as this POKEMON." ,
                "039, Jigglypuff, normal, fairy, Uses its alluring eyes to enrapture its foe. It then sings a pleasing melody that lulls the foe to sleep." ,
                "040, Wigglytuff, normal, fairy, Its body is full of elasticity. By inhaling deeply, it can continue to inflate itself without limit.",
                "041, Zubat, poison, flying, Emits ultrasonic cries while it flies. They act as a sonar used to check for objects in its way.",
                "042, Golbat, poison, flying, It attacks in a stealthy manner, without warning. Its sharp fangs are used to bite and suck blood.",
                "043, Oddish, grass, poison, It may be mistaken for a clump of weeds. If you try to yank it out of the ground, it shrieks horribly." ,
                "044, Gloom, grass, poison, Smells incredibly foul! However, around 1 out of 1,000 people enjoy sniffing its nose-bending stink.",
                "045, Vileplume, grass, poison, Flaps its broad flower petals to scatter its poisonous pollen. The flapping sound is very loud.",
                "046, Paras, bug, grass, Burrows under the ground to gnaw on tree roots. The mushrooms on its back absorb most of the nutrition.",
                "047, Parasect, bug, grass, The bug host is drained of energy by the mushrooms on its back. They appear to do all the thinking.",
                "048, Venonat, bug, poison, Its large eyes act as radars. In a bright place, you can see that they are clusters of many tiny eyes.",
                "049, Venomoth, bug, poison, The powdery scales on its wings are hard to remove. They also contain poison that leaks out on contact.",
                "050, Diglett, ground, NULL, It prefers dark places. It spends most of its time underground, though it may pop up in caves.",
                "051, Dugtrio, ground, NULL, A team of triplets that can burrow over 60 MPH. Due to this, some people think its an earthquake.",
                "052, Meowth, NORMAL, NULL,  Appears to be more active at night. It loves round and shiny things. It cant stop from picking them up." ,
                "053, Persian, NORMAL, NULL,  The gem in its forehead glows on its own! It walks with all the grace and elegance of a proud queen." ,
                "054, Psyduck, WATER, NULL,  Always tormented by headaches. It uses psychic powers, but it is not known if it intends to do so." ,
                "055, Golduck, WATER, NULL,  Its slim and long limbs end in broad flippers. They are used for swimming gracefully in lakes." ,
                "056, Mankey, FIGHTING, NULL,  An agile POKeMON that lives in trees. It angers easily and will not hesitate to attack anything." ,
                "057, Primeape, FIGHTING, NULL,  It stops being angry only when nobody else is around. To view this moment is very difficult." ,
                "058, Growlithe, FIRE, NULL,  A POKeMON with a friendly nature. However, it will bark fiercely at anything invading its territory." ,
                "059, Arcanine, FIRE, NULL,  A legendary POKeMON in China. Many people are charmed by its grace and beauty while running." ,
                "060, Poliwag, WATER, NULL,  The direction of the spiral on the belly differs by area. It is more adept at swimming than walking." ,
                "061, Poliwhirl, WATER, NULL,  Under attack, it uses its belly spiral to put the foe to sleep. It then makes its escape." ,
                "062, Poliwrath, WATER, FIGHTING,  Swims powerfully using all the muscles in its body. It can even overtake champion swimmers." ,
                "063, Abra, PSYCHIC, NULL,  Sleeps 18 hours a day. If it senses danger, it will teleport itself to safety even as it sleeps." ,
                "064, Kadabra, PSYCHIC, NULL,  Many odd things happen if this POKeMON is close by. For example, it makes clocks run backwards." ,
                "065, Alakazam, PSYCHIC, NULL,  A POKeMON that can memorize anything. It never forgets what it learns--thats why this POKeMON is smart." ,
                "066, Machop, FIGHTING, NULL,  Very powerful in spite of its small size. Its mastery of many types of martial arts makes it very tough." ,
                "067, Machoke, FIGHTING, NULL,  The belt around its waist holds back its energy. Without it, this POKeMON would be unstoppable." ,
                "068, Machamp, FIGHTING, NULL,  One arm alone can move mountains. Using all four arms, this POKeMON fires off awesome punches." ,
                "069, Bellsprout, GRASS, POISON,  Prefers hot and humid places. It ensnares tiny insects with its vines and devours them." ,
                "070, Weepinbell, GRASS, POISON,  When hungry, it swallows anything that moves. Its hapless prey is melted inside by strong acids." ,
                "071, Victreebel, GRASS, POISON,  Lures prey with the sweet aroma of honey. Swallowed whole, the prey is melted in a day, bones and all." ,
                "072, Tentacool, WATER, POISON,  It can sometimes be found all dry and shriveled up on a beach. Toss it back into the sea to revive it." ,
                "073, Tentacruel, WATER, POISON,  Its 80 tentacles can stretch and contract freely. They wrap around prey and weaken it with poison." ,
                "074, Geodude, ROCK, GROUND,  Commonly found near mountain trails, etc. If you step on one by accident, it gets angry." ,
                "075, Graveler, ROCK, GROUND,  Often seen rolling down mountain trails. Obstacles are just things to roll straight over, not avoid." ,
                "076, Golem, ROCK, GROUND,  Once it sheds its skin, its body turns tender and whitish. Its hide hardens when its exposed to air." ,
                "077, Ponyta, FIRE, NULL,  Capable of jumping incredibly high. Its hooves and sturdy legs absorb the impact of a hard landing." ,
                "078, Rapidash, FIRE, NULL,  Just loves to run. If it sees something faster than itself, it will give chase at top speed." ,
                "079, Slowpoke, WATER, PSYCHIC,  Incredibly slow and sluggish. It is quite content to loll about without worrying about the time." ,
                "080, Slowbro, WATER, PSYCHIC,  Lives lazily by the sea. If the SHELLDER on its tail comes off, it becomes a SLOWPOKE again." ,
                "081, Magnemite, ELECTRIC, STEEL,  It is born with the ability to defy gravity. It floats in air on powerful electromagnetic waves." ,
                "082, Magneton, ELECTRIC, STEEL,  Generates strange radio signals. It raises the temperature by 3.6F degrees within 3,300 feet." ,
                "083, Farfetchd, NORMAL, FLYING,  Lives where reedy plants grow. They are rarely seen, so its thought their numbers are decreasing." ,
                "084, Doduo, NORMAL, FLYING,  Its short wings make flying difficult. Instead, this POKeMON runs at high speed on developed legs." ,
                "085, Dodrio, NORMAL, FLYING,  One of DODUOs 2 heads splits to form a unique species. It runs close to 40 MPH in prairies." ,
                "086, Seel, WATER, NULL,  Loves freezing cold conditions. Relishes swimming in a frigid climate of around 14F degrees." ,
                "087, Dewgong, WATER, ICE,  Its entire body is a snowy-white. Unharmed by even intense cold, it swims powerfully in icy waters." ,
                "088, Grimer, POISON, NULL,  Made of hardened sludge. It smells too putrid to touch. Even weeds wont grow in its path." ,
                "089, Muk,  POISON, NULL,  Smells so awful, it can cause fainting. Through degeneration, it lost its sense of smell." ,
                "090, Shellder, WATER, NULL,  The shell can withstand any attack. However, when it is open, the tender body is exposed." ,
                "091, Cloyster, WATER, ICE,  For protection, it uses its harder-than-diamonds shell. It also shoots spikes from the shell." ,
                "092, Gastly, GHOST, POISON,  Said to appear in decrepit, deserted buildings. It has no real shape as it appears to be made of a gas." ,
                "093, Haunter, GHOST, POISON,  By licking, it saps the victims life. It causes shaking that wont stop until the victims demise." ,
                "094, Gengar, GHOST, POISON,  A GENGAR is close by if you feel a sudden chill. It may be trying to lay a curse on you." ,
                "095, Onix, ROCK, GROUND,  Burrows at high speed in search of food. The tunnels it leaves are used as homes by DIGLETTs." ,
                "096, Drowzee, PSYCHIC, NULL,  If you sleep by it all the time, it will sometimes show you dreams it has eaten in the past." ,
                "097, Hypno, PSYCHIC, NULL, Avoid eye contact if you come across one. It will try to put you to sleep by using its pendulum." ,
                "098, Krabby, WATER, NULL,  Its pincers are superb weapons. They sometimes break off during battle, but they grow back fast." ,
                "099, Kingler, WATER, NULL,  One claw grew massively and as hard as steel. It has 10,000-HP strength. However, it is too heavy." ,
                "100, Voltorb, ELECTRIC, NULL,  It is said to camouflage itself as a POKe BALL. It will self-destruct with very little stimulus." ,
                "101, Electrode, ELECTRIC, NULL,  Stores electrical energy inside its body. Even the slightest shock could trigger a huge explosion." ,
                "102, Exeggcute, GRASS, PSYCHIC,  The heads attract each other and spin around. There must be 6 heads for it to maintain balance" ,
                "103, Exeggutor, GRASS, PSYCHIC,  Its cries are very noisy. This is because each of the three heads thinks about whatever it likes." ,
                "104, Cubone, GROUND, NULL,  Wears the skull of its deceased mother. Its cries echo inside the skull and come out as a sad melody." ,
                "105, Marowak, GROUND, NULL,  Small and weak, this Pokemon is adept with its Bone Club. It has grown more vicious over the ages." ,
                "106, Hitmonlee, FIGHTING, NULL,  When kicking, the sole of its foot turns as hard as a diamond on impact and destroys its enemy." ,
                "107, Hitmonchan, FIGHTING, NULL,  Punches in corkscrew fashion. It can punch its way through a concrete wall in the same way as a drill." ,
                "108, Lickitung, NORMAL, NULL,  Its tongue spans almost 7 feet and moves more freely than its forelegs. Its licks can cause paralysis." ,
                "109, Koffing, POISON, NULL,  In hot places, its internal gases could expand and explode without any warning. Be very careful!" ,
                "110, Weezing, POISON, NULL,  It lives and grows by absorbing dust, germs, and poison gases that are contained in toxic waste and garbage." ,
                "111, Rhyhorn, GROUND, ROCK,  A Pokemon with a one-track mind. Once it charges, it wont stop running until it falls asleep." ,
                "112, Rhydon, GROUND, ROCK,  Walks on its hind legs. Shows signs of intelligence. Its armor-like hide even repels molten lava." ,
                "113, Chansey, NORMAL, NULL,  A gentle and kind-hearted Pokemon that shares its nutritious eggs if it sees an injured Pokemon." ,
                "114, Tangela, GRASS, NULL, Its identity is obscured by masses of thick, blue vines. The vines are said to never stop growing." ,
                "115, Kangaskhan, NORMAL, NULL,  Raises its young in its belly pouch. Wont run from any fight to keep its young protected." ,
                "116, Horsea, WATER, NULL,  If it senses any danger, it will vigorously spray water or a special type of ink from its mouth." ,
                "117, Seadra, WATER, NULL,  Touching the back fin causes numbness. It hooks its tail to coral to stay in place while sleeping." ,
                "118, Goldeen, WATER, NULL,  When it is time for them to lay eggs, they can be seen swimming up rivers and falls in large groups." ,
                "119, Seaking, WATER, NULL,  It is the males job to make a nest by carving out boulders in a stream using the horn on its head." ,
                "120, Staryu, WATER, NULL, As long as the center section is unharmed, It can grow back fully even if it is chopped to bits." ,
                "121, Starmie, WATER, PSYCHIC, The center section is named the core. People think it is communicating when it glows in 7 colors." ,
                "122, Mr.Mime, PSYCHIC, FAIRY,  Always practices its pantomime act. It makes enemies believe something exists that really doesnt." ,
                "123, Scyther, BUG, FLYING,  Leaps out of tall grass and slices prey with its scythes. The movement looks like that of a ninja." ,
                "124, Jynx, ICE, PSYCHIC,  Appears to move to a rhythm of its own, as if it were dancing. It wiggles its hips as it walks." ,
                "125, Electabuzz, ELECTRIC, NULL,  If a major power outage occurs, it is certain that this Pokemon has eaten electricity at a power plant." ,
                "126, Magmar, FIRE, NULL,  Born in an active volcano. Its body is always cloaked in flames, so it looks like a big ball of fire." ,
                "127, Pinsir, BUG, NULL,  Grips its prey in its pincers and squeezes hard! It cant move if its cold, so it lives in warm places." ,
                "128, Tauros, NORMAL, NULL,  A rowdy Pokemon with a lot of stamina. Once running, it wont stop until it hits something." ,
                "129, Magikarp, WATER, NULL,  Famous for being very unreliable. It can be found swimming in seas, lakes, rivers and shallow puddles." ,
                "130, Gyarados, WATER, FLYING,  Brutally vicious and enormously destructive. Known for totally destroying cities in ancient times." ,
                "131, Lapras, WATER, ICE,  A gentle soul that can read the minds of people. It can ferry people across the sea on its back." ,
                "132, Ditto, NORMAL, NULL,  When it spots an enemy, its body transforms into an almost perfect copy of its opponent." ,
                "133, Eevee, NORMAL, NULL,  Its genetic code is unstable, so it could evolve in a variety of ways. There are only a few alive." ,
                "134, Vaporeon, WATER, NULL,  Its cell structure is similar to water molecules. It will melt away and become invisible in water." ,
                "135, Jolteon, ELECTRIC, NULL,  A sensitive Pokemon that easily becomes sad or angry. Every time its mood changes, it charges power." ,
                "136, Flareon, FIRE, NULL,  It has a flame chamber inside its body. It inhales, then blows out fire that is over 3,000F degrees." ,
                "137, Porygon, NORMAL, NULL,  The only Pokemon people anticipate can fly into space. None has managed the feat yet, however." ,
                "138, Omanyte, ROCK, WATER,  An ancient Pokemon that was recovered from a fossil. It swims by cleverly twisting its 10 tentacles about." ,
                "139, Omastar, ROCK, WATER,  Sharp beaks ring its mouth. Its shell was too big for it to move freely, so it became extinct." ,
                "140, Kabuto, ROCK, WATER,  A Pokemon that was recovered from a fossil. It uses the eyes on its back while hiding on the sea floor." ,
                "141, Kabutops, ROCK, WATER,  A slim and fast swimmer. It slices its prey with its sharp sickles and drinks the body fluids." ,
                "142, Aerodactyl, ROCK, FLYING,  A savage Pokemon that died out in ancient times. It was resurrected using DNA taken from amber." ,
                "143, Snorlax, NORMAL, NULL,  Will eat anything, even if the food happens to be a little moldy. It never gets an upset stomach." ,
                "144, Articuno, ICE, FLYING,  A legendary bird Pokemon. It freezes water that is contained in winter air and makes it snow." ,
                "145, Zapdos, ELECTRIC, FLYING,  This legendary bird Pokemon is said to appear when the sky turns dark and lightning showers down." ,
                "146, Moltres, FIRE, FLYING,  A legendary bird Pokemon. As it flaps its flaming wings, even the night sky will turn red." ,
                "147, Dratini, DRAGON, NULL,  The existence of this mythical Pokemon was only recently confirmed by a fisherman who caught one." ,
                "148, Dragonair, DRAGON, NULL,  According to a witness, its body was surrounded by a strange aura that gave it a mystical look." ,
                "149, Dragonite, DRAGON, FLYING,  It is said that this Pokemon lives somewhere in the sea and that it flies. However, it is only a rumor." ,
                "150, Mewtwo, PSYCHIC, NULL,  Its DNA is almost the same as Mews. However, its size and disposition are vastly different." ,
                "151, Mew,  PSYCHIC, NULL,  When viewed through a microscope, this Pokemons short, fine, delicate hair can be seen."};

        for(int i=0;i!=pokemons.length;i++){
            String[] select= pokemons[i].split(",");
            insertPokemon(select[0],select[1],select[2],select[3],select[4]);
        }
    }

    public void initMedia(Bitmap[] pokes){
        for(Bitmap bmp:pokes) {
            SQLiteDatabase db = this.getWritableDatabase();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            ContentValues values = new ContentValues();
            values.put("image", byteArray);
            db.insert("Media", null, values);
        }
    }

    public boolean insertPokemon(String id, String name, String type1, String type2, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("type1", type1);
        if(!type2.equals("NULL")) contentValues.put("type2", type2);
        contentValues.put("description", description);
        db.insert("Pokemon", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Pokemon where id="+id+"", null);
        return res;
    }

    public String getIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Pokemon where name='"+name+"'", null);
        return res.getString(res.getColumnIndex("id"));
    }

    public ArrayList<byte[]> getAllImages(){
        ArrayList<byte[]> array_list = new ArrayList<byte[]>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Media", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getBlob(res.getColumnIndex("image")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Pokemon", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return array_list;
    }



    }
