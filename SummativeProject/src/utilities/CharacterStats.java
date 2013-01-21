/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

/**
 *
 * @author root
 */
public class CharacterStats {

    float speed;
    int health;
    int damage;

    public CharacterStats(float speed, int health, int damage){
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public float GetSpeed(){
        return speed;
    }

    public void SetSpeed(float speed){
        this.speed = speed;
    }

    public int GetHealth(){
        return health;
    }

    public void SetHealth(int health){
        this.health = health;
    }

    public int GetDamage(){
        return damage;
    }

    public void SetDamage(int damage){
        this.damage = damage;
    }

    public void TakeDamage(int enemyDamage){
        this.health -= enemyDamage;
    }

    public CharacterStats Clone(){
        return new CharacterStats(speed, health, damage);
    }
}
