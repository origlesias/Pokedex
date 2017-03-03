package com.example.oriol.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {
    Bitmap[] pokes= new Bitmap[151];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        DataBaseHandler dbh= new DataBaseHandler(this);
        if(dbh.getAllData().size()==0) {
            dbh.initPokedex();
        }

        if(dbh.getAllImages().size()==0) {
            initPokes();
            dbh.initMedia(pokes);
        }

        ImageView iv = (ImageView) findViewById(R.id.logo);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        iv.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(Splash_Activity.this,Menu.class));
                Splash_Activity.this.finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }



    private void initPokes(){
        pokes[0] = BitmapFactory.decodeResource(getResources(),R.drawable.p001);
        pokes[1] = BitmapFactory.decodeResource(getResources(),R.drawable.p002);
        pokes[2] = BitmapFactory.decodeResource(getResources(),R.drawable.p003);
        pokes[3] = BitmapFactory.decodeResource(getResources(),R.drawable.p004);
        pokes[4] = BitmapFactory.decodeResource(getResources(),R.drawable.p005);
        pokes[5] = BitmapFactory.decodeResource(getResources(),R.drawable.p006);
        pokes[6] = BitmapFactory.decodeResource(getResources(),R.drawable.p007);
        pokes[7] = BitmapFactory.decodeResource(getResources(),R.drawable.p008);
        pokes[8] = BitmapFactory.decodeResource(getResources(),R.drawable.p009);
        pokes[9] = BitmapFactory.decodeResource(getResources(),R.drawable.p010);
        pokes[10] = BitmapFactory.decodeResource(getResources(),R.drawable.p011);
        pokes[11] = BitmapFactory.decodeResource(getResources(),R.drawable.p012);
        pokes[12] = BitmapFactory.decodeResource(getResources(),R.drawable.p013);
        pokes[13] = BitmapFactory.decodeResource(getResources(),R.drawable.p014);
        pokes[14] = BitmapFactory.decodeResource(getResources(),R.drawable.p015);
        pokes[15] = BitmapFactory.decodeResource(getResources(),R.drawable.p016);
        pokes[16] = BitmapFactory.decodeResource(getResources(),R.drawable.p017);
        pokes[17] = BitmapFactory.decodeResource(getResources(),R.drawable.p018);
        pokes[18] = BitmapFactory.decodeResource(getResources(),R.drawable.p019);
        pokes[19] = BitmapFactory.decodeResource(getResources(),R.drawable.p020);
        pokes[20] = BitmapFactory.decodeResource(getResources(),R.drawable.p021);
        pokes[21] = BitmapFactory.decodeResource(getResources(),R.drawable.p022);
        pokes[22] = BitmapFactory.decodeResource(getResources(),R.drawable.p023);
        pokes[23] = BitmapFactory.decodeResource(getResources(),R.drawable.p024);
        pokes[24] = BitmapFactory.decodeResource(getResources(),R.drawable.p025);
        pokes[25] = BitmapFactory.decodeResource(getResources(),R.drawable.p026);
        pokes[26] = BitmapFactory.decodeResource(getResources(),R.drawable.p027);
        pokes[27] = BitmapFactory.decodeResource(getResources(),R.drawable.p028);
        pokes[28] = BitmapFactory.decodeResource(getResources(),R.drawable.p029);
        pokes[29] = BitmapFactory.decodeResource(getResources(),R.drawable.p030);
        pokes[30] = BitmapFactory.decodeResource(getResources(),R.drawable.p031);
        pokes[31] = BitmapFactory.decodeResource(getResources(),R.drawable.p032);
        pokes[32] = BitmapFactory.decodeResource(getResources(),R.drawable.p033);
        pokes[33] = BitmapFactory.decodeResource(getResources(),R.drawable.p034);
        pokes[34] = BitmapFactory.decodeResource(getResources(),R.drawable.p035);
        pokes[35] = BitmapFactory.decodeResource(getResources(),R.drawable.p036);
        pokes[36] = BitmapFactory.decodeResource(getResources(),R.drawable.p037);
        pokes[37] = BitmapFactory.decodeResource(getResources(),R.drawable.p038);
        pokes[38] = BitmapFactory.decodeResource(getResources(),R.drawable.p039);
        pokes[39] = BitmapFactory.decodeResource(getResources(),R.drawable.p040);
        pokes[40] = BitmapFactory.decodeResource(getResources(),R.drawable.p041);
        pokes[41] = BitmapFactory.decodeResource(getResources(),R.drawable.p042);
        pokes[42] = BitmapFactory.decodeResource(getResources(),R.drawable.p043);
        pokes[43] = BitmapFactory.decodeResource(getResources(),R.drawable.p044);
        pokes[44] = BitmapFactory.decodeResource(getResources(),R.drawable.p045);
        pokes[45] = BitmapFactory.decodeResource(getResources(),R.drawable.p046);
        pokes[46] = BitmapFactory.decodeResource(getResources(),R.drawable.p047);
        pokes[47] = BitmapFactory.decodeResource(getResources(),R.drawable.p048);
        pokes[48] = BitmapFactory.decodeResource(getResources(),R.drawable.p049);
        pokes[49] = BitmapFactory.decodeResource(getResources(),R.drawable.p050);
        pokes[50] = BitmapFactory.decodeResource(getResources(),R.drawable.p051);
        pokes[51] = BitmapFactory.decodeResource(getResources(),R.drawable.p052);
        pokes[52] = BitmapFactory.decodeResource(getResources(),R.drawable.p053);
        pokes[53] = BitmapFactory.decodeResource(getResources(),R.drawable.p054);
        pokes[54] = BitmapFactory.decodeResource(getResources(),R.drawable.p055);
        pokes[55] = BitmapFactory.decodeResource(getResources(),R.drawable.p056);
        pokes[56] = BitmapFactory.decodeResource(getResources(),R.drawable.p057);
        pokes[57] = BitmapFactory.decodeResource(getResources(),R.drawable.p058);
        pokes[58] = BitmapFactory.decodeResource(getResources(),R.drawable.p059);
        pokes[59] = BitmapFactory.decodeResource(getResources(),R.drawable.p060);
        pokes[60] = BitmapFactory.decodeResource(getResources(),R.drawable.p061);
        pokes[61] = BitmapFactory.decodeResource(getResources(),R.drawable.p062);
        pokes[62] = BitmapFactory.decodeResource(getResources(),R.drawable.p063);
        pokes[63] = BitmapFactory.decodeResource(getResources(),R.drawable.p064);
        pokes[64] = BitmapFactory.decodeResource(getResources(),R.drawable.p065);
        pokes[65] = BitmapFactory.decodeResource(getResources(),R.drawable.p066);
        pokes[66] = BitmapFactory.decodeResource(getResources(),R.drawable.p067);
        pokes[67] = BitmapFactory.decodeResource(getResources(),R.drawable.p068);
        pokes[68] = BitmapFactory.decodeResource(getResources(),R.drawable.p069);
        pokes[69] = BitmapFactory.decodeResource(getResources(),R.drawable.p070);
        pokes[70] = BitmapFactory.decodeResource(getResources(),R.drawable.p071);
        pokes[71] = BitmapFactory.decodeResource(getResources(),R.drawable.p072);
        pokes[72] = BitmapFactory.decodeResource(getResources(),R.drawable.p073);
        pokes[73] = BitmapFactory.decodeResource(getResources(),R.drawable.p074);
        pokes[74] = BitmapFactory.decodeResource(getResources(),R.drawable.p075);
        pokes[75] = BitmapFactory.decodeResource(getResources(),R.drawable.p076);
        pokes[76] = BitmapFactory.decodeResource(getResources(),R.drawable.p077);
        pokes[77] = BitmapFactory.decodeResource(getResources(),R.drawable.p078);
        pokes[78] = BitmapFactory.decodeResource(getResources(),R.drawable.p079);
        pokes[79] = BitmapFactory.decodeResource(getResources(),R.drawable.p080);
        pokes[80] = BitmapFactory.decodeResource(getResources(),R.drawable.p081);
        pokes[81] = BitmapFactory.decodeResource(getResources(),R.drawable.p082);
        pokes[82] = BitmapFactory.decodeResource(getResources(),R.drawable.p083);
        pokes[83] = BitmapFactory.decodeResource(getResources(),R.drawable.p084);
        pokes[84] = BitmapFactory.decodeResource(getResources(),R.drawable.p085);
        pokes[85] = BitmapFactory.decodeResource(getResources(),R.drawable.p086);
        pokes[86] = BitmapFactory.decodeResource(getResources(),R.drawable.p087);
        pokes[87] = BitmapFactory.decodeResource(getResources(),R.drawable.p088);
        pokes[88] = BitmapFactory.decodeResource(getResources(),R.drawable.p089);
        pokes[89] = BitmapFactory.decodeResource(getResources(),R.drawable.p090);
        pokes[90] = BitmapFactory.decodeResource(getResources(),R.drawable.p091);
        pokes[91] = BitmapFactory.decodeResource(getResources(),R.drawable.p092);
        pokes[92] = BitmapFactory.decodeResource(getResources(),R.drawable.p093);
        pokes[93] = BitmapFactory.decodeResource(getResources(),R.drawable.p094);
        pokes[94] = BitmapFactory.decodeResource(getResources(),R.drawable.p095);
        pokes[95] = BitmapFactory.decodeResource(getResources(),R.drawable.p096);
        pokes[96] = BitmapFactory.decodeResource(getResources(),R.drawable.p097);
        pokes[97] = BitmapFactory.decodeResource(getResources(),R.drawable.p098);
        pokes[98] = BitmapFactory.decodeResource(getResources(),R.drawable.p099);
        pokes[99] = BitmapFactory.decodeResource(getResources(),R.drawable.p100);
        pokes[100] = BitmapFactory.decodeResource(getResources(),R.drawable.p101);
        pokes[101] = BitmapFactory.decodeResource(getResources(),R.drawable.p102);
        pokes[102] = BitmapFactory.decodeResource(getResources(),R.drawable.p103);
        pokes[103] = BitmapFactory.decodeResource(getResources(),R.drawable.p104);
        pokes[104] = BitmapFactory.decodeResource(getResources(),R.drawable.p105);
        pokes[105] = BitmapFactory.decodeResource(getResources(),R.drawable.p106);
        pokes[106] = BitmapFactory.decodeResource(getResources(),R.drawable.p107);
        pokes[107] = BitmapFactory.decodeResource(getResources(),R.drawable.p108);
        pokes[108] = BitmapFactory.decodeResource(getResources(),R.drawable.p109);
        pokes[109] = BitmapFactory.decodeResource(getResources(),R.drawable.p110);
        pokes[110] = BitmapFactory.decodeResource(getResources(),R.drawable.p111);
        pokes[111] = BitmapFactory.decodeResource(getResources(),R.drawable.p112);
        pokes[112] = BitmapFactory.decodeResource(getResources(),R.drawable.p113);
        pokes[113] = BitmapFactory.decodeResource(getResources(),R.drawable.p114);
        pokes[114] = BitmapFactory.decodeResource(getResources(),R.drawable.p115);
        pokes[115] = BitmapFactory.decodeResource(getResources(),R.drawable.p116);
        pokes[116] = BitmapFactory.decodeResource(getResources(),R.drawable.p117);
        pokes[117] = BitmapFactory.decodeResource(getResources(),R.drawable.p118);
        pokes[118] = BitmapFactory.decodeResource(getResources(),R.drawable.p119);
        pokes[119] = BitmapFactory.decodeResource(getResources(),R.drawable.p120);
        pokes[120] = BitmapFactory.decodeResource(getResources(),R.drawable.p121);
        pokes[121] = BitmapFactory.decodeResource(getResources(),R.drawable.p122);
        pokes[122] = BitmapFactory.decodeResource(getResources(),R.drawable.p123);
        pokes[123] = BitmapFactory.decodeResource(getResources(),R.drawable.p124);
        pokes[124] = BitmapFactory.decodeResource(getResources(),R.drawable.p125);
        pokes[125] = BitmapFactory.decodeResource(getResources(),R.drawable.p126);
        pokes[126] = BitmapFactory.decodeResource(getResources(),R.drawable.p127);
        pokes[127] = BitmapFactory.decodeResource(getResources(),R.drawable.p128);
        pokes[128] = BitmapFactory.decodeResource(getResources(),R.drawable.p129);
        pokes[129] = BitmapFactory.decodeResource(getResources(),R.drawable.p130);
        pokes[130] = BitmapFactory.decodeResource(getResources(),R.drawable.p131);
        pokes[131] = BitmapFactory.decodeResource(getResources(),R.drawable.p132);
        pokes[132] = BitmapFactory.decodeResource(getResources(),R.drawable.p133);
        pokes[133] = BitmapFactory.decodeResource(getResources(),R.drawable.p134);
        pokes[134] = BitmapFactory.decodeResource(getResources(),R.drawable.p135);
        pokes[135] = BitmapFactory.decodeResource(getResources(),R.drawable.p136);
        pokes[136] = BitmapFactory.decodeResource(getResources(),R.drawable.p137);
        pokes[137] = BitmapFactory.decodeResource(getResources(),R.drawable.p138);
        pokes[138] = BitmapFactory.decodeResource(getResources(),R.drawable.p139);
        pokes[139] = BitmapFactory.decodeResource(getResources(),R.drawable.p140);
        pokes[140] = BitmapFactory.decodeResource(getResources(),R.drawable.p141);
        pokes[141] = BitmapFactory.decodeResource(getResources(),R.drawable.p142);
        pokes[142] = BitmapFactory.decodeResource(getResources(),R.drawable.p143);
        pokes[143] = BitmapFactory.decodeResource(getResources(),R.drawable.p144);
        pokes[144] = BitmapFactory.decodeResource(getResources(),R.drawable.p145);
        pokes[145] = BitmapFactory.decodeResource(getResources(),R.drawable.p146);
        pokes[146] = BitmapFactory.decodeResource(getResources(),R.drawable.p147);
        pokes[147] = BitmapFactory.decodeResource(getResources(),R.drawable.p148);
        pokes[148] = BitmapFactory.decodeResource(getResources(),R.drawable.p149);
        pokes[149] = BitmapFactory.decodeResource(getResources(),R.drawable.p150);
        pokes[150] = BitmapFactory.decodeResource(getResources(),R.drawable.p151);
    }

}
