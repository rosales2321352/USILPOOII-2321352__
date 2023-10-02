using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CoinSpawnerController : MonoBehaviour
{
    // Start is called before the first frame update

    float timer;
    public GameObject coinPrefab;

    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {

        timer += Time.deltaTime;
        if(timer >= 2f){
            timer = 0;
            float x = Random.Range(-30f,20f);
            Vector3 pos = new Vector3(x,0,0);
            Quaternion rot = new Quaternion();
            Instantiate(coinPrefab, pos, rot);
        }
    }
}
