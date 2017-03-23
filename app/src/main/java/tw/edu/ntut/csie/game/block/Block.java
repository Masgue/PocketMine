package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public abstract class Block implements GameObject {
    protected static final int BLOCK_HEIGHT = 60;

//    private MovingBitmap block1_hit_once;
//    private MovingBitmap block2_hit_twice;
//    private MovingBitmap block3_unbreakable;
//    private MovingBitmap block4_coal;
//    private MovingBitmap block5_gold;
//    private MovingBitmap block6_iron;
//    private MovingBitmap block7_diamond;
//    private MovingBitmap block8_ruby;
    protected MovingBitmap _picture;
    protected int _blockType;
    protected int _durability;
    protected int _points;
    protected int _arrayX;
    protected int _arrayY;
    protected int _actualX;
    protected int _actualY;
    protected boolean _isVisible;

    public Block() {

    }

    public Block(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture) {
        _blockType = blockType;
        _arrayX = arrayX;
        _arrayY = arrayY;
        _picture = picture;
        CalculateXY(viewHeight);
    }

//    public Block(int blockType, int arrayX, int arrayY, int viewHeight) {
////        block1_hit_once = new MovingBitmap(R.drawable.block1_hit_once);
////        block2_hit_twice = new MovingBitmap(R.drawable.block2_hit_twice);
////        block3_unbreakable = new MovingBitmap(R.drawable.block3_unbreakable);
////        block4_coal = new MovingBitmap(R.drawable.block4_coal);
////        block5_gold = new MovingBitmap(R.drawable.block5_gold);
////        block6_iron = new MovingBitmap(R.drawable.block6_iron);
////        block7_diamond = new MovingBitmap(R.drawable.block7_diamond);
////        block8_ruby = new MovingBitmap(R.drawable.block8_ruby);
//        _blockType = blockType;
//        _arrayX = arrayX;
//        _arrayY = arrayY;
//        _viewHeight = viewHeight;
//    }

    @Override
    public void release() {
//        block1_hit_once.release();
//        block2_hit_twice.release();
//        block3_unbreakable.release();
//        block4_coal.release();
//        block5_gold.release();
//        block6_iron.release();
//        block7_diamond.release();
//        block8_ruby.release();
//
//        block1_hit_once = null;
//        block2_hit_twice = null;
//        block3_unbreakable = null;
//        block4_coal = null;
//        block5_gold = null;
//        block6_iron = null;
//        block7_diamond = null;
//        block8_ruby = null;
    }

    @Override
    public void move() { }

    @Override
    public void show() {
        _picture.setLocation(_actualX, _actualY);
        _picture.show();
//       for (int i = 0; i < 30; i++)
//       {
//           for (int j = 0; j < 6; j++)
//           {
//               if (blockType == 1) {
//                   CalculateXY();
//                   block1_hit_once.setLocation(x, y);
//                   block1_hit_once.show();
//               } else if (blockType == 2) {
//                   CalculateXY();
//                   block2_hit_twice.setLocation(x, y);
//                   block2_hit_twice.show();
//               }
//               else if (blockType == 3) {
//                   CalculateXY();
//                   block3_unbreakable.setLocation(x, y);
//                   block3_unbreakable.show();
//               }
//               else if (blockType == 4) {
//                   CalculateXY();
//                   block4_coal.setLocation(x, y);
//                   block4_coal.show();
//               }
//               else if (blockType == 5) {
//                   CalculateXY();
//                   block5_gold.setLocation(x, y);
//                   block5_gold.show();
//               }
//               else if (blockType == 6) {
//                   CalculateXY();
//                   block6_iron.setLocation(x, y);
//                   block6_iron.show();
//               }
//               else if (blockType == 7) {
//                   CalculateXY();
//                   block7_diamond.setLocation(x, y);
//                   block7_diamond.show();
//               }
//               else if (blockType == 8) {
//                   CalculateXY();
//                   block8_ruby.setLocation(x, y);
//                   block8_ruby.show();
//               }
//           }
//       }
    }

    protected void CalculateXY(int viewHeight) {
        _actualY = 10 + BLOCK_HEIGHT * _arrayY;
        _actualX = 160 + BLOCK_HEIGHT * _arrayX - viewHeight;
    }

    public int GetPoints() {
        return  _points;
    }

    public int GetDurability() {
        return _durability;
    }
}
